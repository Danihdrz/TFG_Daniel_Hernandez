import { useEffect, useState } from "react";
import api from "../services/api";
import DashboardLayout from "../layout/DashboardLayout";
import {
    PieChart, Pie, Cell, Tooltip,
    LineChart, Line, XAxis, YAxis, CartesianGrid,
    ResponsiveContainer
} from "recharts";

export default function Admin() {
    const [usuarios, setUsuarios] = useState([]);
    const [stats, setStats] = useState(null);
    const [clases, setClases] = useState([]);
    const [activeTab, setActiveTab] = useState("overview");

    const [userForm, setUserForm] = useState({ nombre: "", email: "", password: "", rol: "SOCIO" });
    const [claseForm, setClaseForm] = useState({ nombre: "", descripcion: "", fechaHora: "", duracion: 60, aforoMax: 10 });
    const [editUserId, setEditUserId] = useState(null);

    useEffect(() => {
        cargarTodo();
    }, []);

    const cargarTodo = async () => {
        try {
            const [uRes, sRes, cRes] = await Promise.all([
                api.get("/usuarios"),
                api.get("/dashboard/metrics"),
                api.get("/clases")
            ]);
            setUsuarios(uRes.data);
            setStats(sRes.data);
            setClases(cRes.data);
        } catch (err) {
            console.error("Error al cargar datos:", err);
        }
    };

    // User Handlers
    const handleUserChange = (e) => setUserForm({ ...userForm, [e.target.name]: e.target.value });
    const saveUser = async (e) => {
        e.preventDefault();
        if (editUserId) await api.put(`/usuarios/${editUserId}`, userForm);
        else await api.post("/usuarios", userForm);
        setUserForm({ nombre: "", email: "", password: "", rol: "SOCIO" });
        setEditUserId(null);
        cargarTodo();
    };

    // Class Handlers
    const handleClaseChange = (e) => setClaseForm({ ...claseForm, [e.target.name]: e.target.value });
    const createClase = async (e) => {
        e.preventDefault();
        await api.post("/clases", claseForm);
        setClaseForm({ nombre: "", descripcion: "", fechaHora: "", duracion: 60, aforoMax: 10 });
        cargarTodo();
    };

    if (!stats) return <p>Cargando panel...</p>;

    const COLORS = ["#00C49F", "#0088FE", "#FF8042"];
    const pieData = [
        { name: "Socios", value: stats.socios },
        { name: "Entrenadores", value: stats.entrenadores },
        { name: "Admins", value: stats.admins }
    ];

    return (
        <DashboardLayout title="Administración del Sistema">
            <div className="admin-container">
                {/* Tabs de Navegación Interna */}
                <nav className="internal-tabs">
                    <button onClick={() => setActiveTab("overview")} className={activeTab === "overview" ? "active" : ""}>Vista General</button>
                    <button onClick={() => setActiveTab("users")} className={activeTab === "users" ? "active" : ""}>Gestión Usuarios</button>
                    <button onClick={() => setActiveTab("classes")} className={activeTab === "classes" ? "active" : ""}>Gestión Clases</button>
                </nav>

                <hr />

                {activeTab === "overview" && (
                    <section className="admin-overview">
                        <div className="stats-grid">
                            <div className="stat-card"><h3>Ingresos</h3><p>€{stats.ingresos}</p></div>
                            <div className="stat-card"><h3>Total Usuarios</h3><p>{stats.totalUsuarios}</p></div>
                            <div className="stat-card"><h3>Socios</h3><p>{stats.socios}</p></div>
                            <div className="stat-card"><h3>Entrenadores</h3><p>{stats.entrenadores}</p></div>
                        </div>

                        <div className="charts-row">
                            <div className="chart-container">
                                <h3>Distribución de Roles</h3>
                                <PieChart width={300} height={250}>
                                    <Pie data={pieData} dataKey="value" outerRadius={80} label>
                                        {pieData.map((_, i) => <Cell key={i} fill={COLORS[i]} />)}
                                    </Pie>
                                    <Tooltip />
                                </PieChart>
                            </div>
                        </div>
                    </section>
                )}

                {activeTab === "users" && (
                    <section className="admin-users">
                        <div className="form-section">
                            <h3>{editUserId ? "Editar" : "Nuevo"} Usuario</h3>
                            <form onSubmit={saveUser}>
                                <input name="nombre" placeholder="Nombre" value={userForm.nombre} onChange={handleUserChange} />
                                <input name="email" placeholder="Email" value={userForm.email} onChange={handleUserChange} />
                                {!editUserId && <input name="password" type="password" placeholder="Password" value={userForm.password} onChange={handleUserChange} />}
                                <select name="rol" value={userForm.rol} onChange={handleUserChange}>
                                    <option value="ADMIN">ADMIN</option>
                                    <option value="ENTRENADOR">ENTRENADOR</option>
                                    <option value="SOCIO">SOCIO</option>
                                </select>
                                <button type="submit">{editUserId ? "Actualizar" : "Crear"}</button>
                            </form>
                        </div>
                        <table border="1">
                            <thead><tr><th>ID</th><th>Nombre</th><th>Email</th><th>Rol</th><th>Acciones</th></tr></thead>
                            <tbody>
                                {usuarios.map(u => (
                                    <tr key={u.idUsuario}>
                                        <td>{u.idUsuario}</td>
                                        <td>{u.nombre}</td>
                                        <td>{u.email}</td>
                                        <td>{u.rol}</td>
                                        <td>
                                            <button onClick={() => { setEditUserId(u.idUsuario); setUserForm({ nombre: u.nombre, email: u.email, rol: u.rol }); }}>Editar</button>
                                            <button onClick={async () => { await api.delete(`/usuarios/${u.idUsuario}`); cargarTodo(); }}>Eliminar</button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </section>
                )}

                {activeTab === "classes" && (
                    <section className="admin-classes">
                        <div className="form-section">
                            <h3>Nueva Clase</h3>
                            <form onSubmit={createClase}>
                                <input name="nombre" placeholder="Nombre" value={claseForm.nombre} onChange={handleClaseChange} />
                                <input name="fechaHora" type="datetime-local" value={claseForm.fechaHora} onChange={handleClaseChange} />
                                <input name="duracion" type="number" placeholder="Duración" value={claseForm.duracion} onChange={handleClaseChange} />
                                <input name="aforoMax" type="number" placeholder="Aforo" value={claseForm.aforoMax} onChange={handleClaseChange} />
                                <button type="submit">Crear Clase</button>
                            </form>
                        </div>
                        <table border="1">
                            <thead><tr><th>Nombre</th><th>Fecha</th><th>Aforo</th></tr></thead>
                            <tbody>
                                {clases.map(c => (
                                    <tr key={c.id}>
                                        <td>{c.nombre}</td>
                                        <td>{c.fechaHora}</td>
                                        <td>{c.aforoMax}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </section>
                )}
            </div>
        </DashboardLayout>
    );
}

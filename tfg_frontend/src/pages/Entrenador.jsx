import { useEffect, useState } from "react";
import api from "../services/api";
import DashboardLayout from "../layout/DashboardLayout";
import "./Entrenador.css";

export default function Entrenador() {
    const [clases, setClases] = useState([]);
    const [loading, setLoading] = useState(true);
    const [selectedClase, setSelectedClase] = useState(null);
    const [alumnos, setAlumnos] = useState([]);
    const [loadingAlumnos, setLoadingAlumnos] = useState(false);

    useEffect(() => {
        cargarClases();
    }, []);

    const cargarClases = async () => {
        try {
            const res = await api.get("/entrenador/clases");
            if (Array.isArray(res.data)) {
                setClases(res.data);
            } else {
                // Mock data for demo
                setClases([
                    { idClase: 1, nombre: "Crossfit Avanzado", descripcion: "Entrenamiento de alta intensidad para atletas experimentados.", fechaHora: "2024-05-10T10:00:00", duracion: 60, aforoMax: 15, inscritos: 12 },
                    { idClase: 2, nombre: "Yoga Flow", descripcion: "Encuentra tu equilibrio y mejora tu flexibilidad en esta sesión fluida.", fechaHora: "2024-05-12T09:00:00", duracion: 90, aforoMax: 20, inscritos: 8 },
                    { idClase: 3, nombre: "Spinning Pro", descripcion: "Desafía tus límites cardiovasculares con nuestra sesión de ciclo indoor.", fechaHora: "2024-05-15T18:30:00", duracion: 45, aforoMax: 10, inscritos: 10 }
                ]);
            }
        } catch (err) {
            console.error("Error cargando clases:", err);
        } finally {
            setLoading(false);
        }
    };

    const verAlumnos = async (clase) => {
        setSelectedClase(clase);
        setLoadingAlumnos(true);
        try {
            const res = await api.get(`/entrenador/clases/${clase.idClase}/alumnos`);
            if (Array.isArray(res.data)) {
                setAlumnos(res.data);
            } else {
                // Mock alumnos
                setAlumnos([
                    { idSocio: 1, nombre: "Juan Pérez", email: "juan@example.com" },
                    { idSocio: 2, nombre: "María García", email: "maria@example.com" },
                    { idSocio: 3, nombre: "Carlos Rodríguez", email: "carlos@example.com" }
                ]);
            }
        } catch (err) {
            console.error("Error cargando alumnos:", err);
        } finally {
            setLoadingAlumnos(false);
        }
    };

    const formatFecha = (fechaStr) => {
        const options = { weekday: 'short', month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit' };
        return new Date(fechaStr).toLocaleDateString('es-ES', options);
    };

    return (
        <DashboardLayout title="Panel de Control">
            <div className="trainer-container">
                <header className="trainer-header">
                    <h1>¡Hola, Entrenador! 👋</h1>
                    <p>Gestiona tus clases y realiza el seguimiento de tus alumnos.</p>
                </header>

                <div className="stats-grid">
                    <div className="stat-card">
                        <h3>Sesiones Totales</h3>
                        <p>{clases.length}</p>
                    </div>
                    <div className="stat-card">
                        <h3>Alumnos Activos</h3>
                        <p>{clases.reduce((acc, curr) => acc + (curr.inscritos || 0), 0)}</p>
                    </div>
                    <div className="stat-card">
                        <h3>Ocupación Media</h3>
                        <p>{clases.length > 0 ? Math.round((clases.reduce((acc, curr) => acc + (curr.inscritos || 0), 0) / clases.reduce((acc, curr) => acc + curr.aforoMax, 0)) * 100) : 0}%</p>
                    </div>
                </div>

                <section className="classes-section">
                    <h2>📅 Mis Próximas Clases</h2>
                    {loading ? (
                        <div className="loading">Cargando sesiones...</div>
                    ) : (
                        <div className="classes-grid">
                            {clases.map((clase) => (
                                <div key={clase.idClase} className="class-card">
                                    <div className="class-header">
                                        <h3>{clase.nombre}</h3>
                                    </div>
                                    <div className="class-body">
                                        <div className="class-info">
                                            <div className="info-item">
                                                <span className="info-icon">📅</span>
                                                <span>{formatFecha(clase.fechaHora)}</span>
                                            </div>
                                            <div className="info-item">
                                                <span className="info-icon">⏱️</span>
                                                <span>{clase.duracion} min</span>
                                            </div>
                                            <p className="class-description">{clase.descripcion}</p>
                                        </div>
                                    </div>
                                    <div className="class-footer">
                                        <span className={`aforo-badge ${clase.inscritos >= clase.aforoMax ? 'full' : ''}`}>
                                            👥 {clase.inscritos} / {clase.aforoMax}
                                        </span>
                                        <button className="btn-view-students" onClick={() => verAlumnos(clase)}>
                                            Ver Alumnos
                                        </button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </section>

                {/* MODAL DE ALUMNOS */}
                {selectedClase && (
                    <div className="modal-overlay" onClick={() => setSelectedClase(null)}>
                        <div className="modal-content" onClick={(e) => e.stopPropagation()}>
                            <button className="modal-close" onClick={() => setSelectedClase(null)}>×</button>
                            <h2>Alumnos en {selectedClase.nombre}</h2>
                            <p>{formatFecha(selectedClase.fechaHora)}</p>
                            
                            {loadingAlumnos ? (
                                <p>Cargando lista...</p>
                            ) : (
                                <ul className="student-list">
                                    {alumnos.length > 0 ? alumnos.map(alumno => (
                                        <li key={alumno.idSocio} className="student-item">
                                            <div className="student-info">
                                                <h4>{alumno.nombre}</h4>
                                                <p>{alumno.email}</p>
                                            </div>
                                            <div className="student-actions">
                                                <span className="info-icon">✅</span>
                                            </div>
                                        </li>
                                    )) : (
                                        <p>No hay alumnos inscritos en esta clase todavía.</p>
                                    )}
                                </ul>
                            )}
                        </div>
                    </div>
                )}
            </div>
        </DashboardLayout>
    );
}

import { logout } from "../auth/auth.js";
import { Link, useLocation } from "react-router-dom";

export default function DashboardLayout({ title, children }) {
    const location = useLocation();
    const role = location.pathname.split("/")[1]; // e.g., "entrenador", "admin", "socio"

    return (
        <div className="dashboard-wrapper">
            <aside className="dashboard-sidebar">
                <div className="sidebar-logo">
                    <h2>GIMNASIO</h2>
                </div>
                <nav className="sidebar-nav">
                    <Link to={`/${role}`} className={location.pathname === `/${role}` ? "active" : ""}>
                        Dashboard
                    </Link>
                    {role === "admin" && (
                        <>
                            <Link to="/admin/usuarios">Usuarios</Link>
                            <Link to="/admin/clases">Clases</Link>
                        </>
                    )}
                    {role === "entrenador" && (
                        <>
                            <Link to="/entrenador/perfil">Mi Perfil</Link>
                        </>
                    )}
                </nav>
                <div className="sidebar-footer">
                    <button className="btn-logout" onClick={logout}>Cerrar Sesión</button>
                </div>
            </aside>

            <div className="dashboard-main">
                <header className="dashboard-header">
                    <h2>{title}</h2>
                    <div className="user-profile">
                        <span>{role.toUpperCase()}</span>
                    </div>
                </header>

                <main className="dashboard-content">
                    {children}
                </main>
            </div>
        </div>
    );
}

import { useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import api from "../services/api";
import { getToken } from "./auth";

export default function ProtectedRoute({ role, children }) {
    const [status, setStatus] = useState({ loading: true, allowed: false });

    useEffect(() => {
        const token = getToken();
        if (!token) {
            setStatus({ loading: false, allowed: false });
            return;
        }

        api.get("/usuarios/perfil")
            .then((res) => {
                const allowed = !role || res.data.rol === role;
                setStatus({ loading: false, allowed });
            })
            .catch(() => setStatus({ loading: false, allowed: false }));
    }, [role]);

    if (status.loading) {
        return <p>Cargando...</p>;
    }

    if (!status.allowed) {
        return <Navigate to="/" />;
    }

    return children;
}

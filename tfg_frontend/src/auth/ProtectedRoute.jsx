import { Navigate } from "react-router-dom";
import { getRole } from "./auth";

export default function ProtectedRoute({ role, children }) {

    const userRole = getRole();

    if (!userRole) {
        return <Navigate to="/" />;
    }

    if (role && userRole !== role) {
        return <Navigate to="/" />;
    }

    return children;
}
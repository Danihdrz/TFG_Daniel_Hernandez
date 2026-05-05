import { useState } from "react";
import api from "../services/api";
import { setToken, setRole } from "../auth/auth";
import { useNavigate } from "react-router-dom";

export default function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        const res = await api.post("/auth/login", {
            email,
            password
        });

        const token = res.data.token;
        const rol = res.data.rol;

        setToken(token);
        setRole(rol);

        // 🔥 REDIRECCIÓN POR ROL
        if (rol === "ADMIN") navigate("/admin");
        else if (rol === "ENTRENADOR") navigate("/entrenador");
        else navigate("/socio");
    };

    return (
        <form onSubmit={handleLogin}>
            <input
                placeholder="email"
                onChange={(e) => setEmail(e.target.value)}
            />

            <input
                type="password"
                placeholder="password"
                onChange={(e) => setPassword(e.target.value)}
            />

            <button>Login</button>
        </form>
    );
}
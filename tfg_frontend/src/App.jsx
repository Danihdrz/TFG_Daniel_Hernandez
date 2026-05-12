import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Admin from "./pages/Admin";
import Entrenador from "./pages/Entrenador";
import Socio from "./pages/Socio";

import ProtectedRoute from "./auth/ProtectedRoute";
import SiteHeader from "./layout/SiteHeader";

export default function App() {
    return (
        <BrowserRouter>

            <SiteHeader />

            <Routes>

                {/* 🔓 LOGIN PÚBLICO */}
                <Route path="/" element={<Login />} />

                {/* 🔴 ADMIN */}
                <Route
                    path="/admin/*"
                    element={
                        <ProtectedRoute role="ADMIN">
                            <Admin />
                        </ProtectedRoute>
                    }
                />

                {/* 🟠 ENTRENADOR */}
                <Route
                    path="/entrenador/*"
                    element={
                        <ProtectedRoute role="ENTRENADOR">
                            <Entrenador />
                        </ProtectedRoute>
                    }
                />

                {/* 🟢 SOCIO */}
                <Route
                    path="/socio/*"
                    element={
                        <ProtectedRoute role="SOCIO">
                            <Socio />
                        </ProtectedRoute>
                    }
                />

            </Routes>

        </BrowserRouter>
    );
}
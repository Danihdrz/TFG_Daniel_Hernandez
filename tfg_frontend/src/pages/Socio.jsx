import { useEffect, useState } from "react";
import api from "../services/api";
import DashboardLayout from "../layout/DashboardLayout";

export default function Socio() {

    const [perfil, setPerfil] = useState(null);

    useEffect(() => {
        cargarPerfil();
    }, []);

    const cargarPerfil = async () => {
        try {
            const res = await api.get("/usuarios/perfil");
            setPerfil(res.data);
        } catch (err) {
            console.log(err);
        }
    };

    return (
        <DashboardLayout title="MI PERFIL">

            {perfil ? (
                <div>
                    <p>Nombre: {perfil.nombre}</p>
                    <p>Email: {perfil.email}</p>
                    <p>Rol: {perfil.rol}</p>
                </div>
            ) : (
                <p>Cargando...</p>
            )}

        </DashboardLayout>
    );
}
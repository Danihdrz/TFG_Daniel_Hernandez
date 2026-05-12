import React from "react";
import "./SiteHeader.css";

export default function SiteHeader(){
  return (
    <header className="site-header">
      <div className="container">
        <div className="logo">Gimasio</div>
        <nav className="site-nav">
          <a href="/">Inicio</a>
          <a href="/admin">Admin</a>
          <a href="/entrenador">Entrenador</a>
          <a href="/socio">Socio</a>
        </nav>
        <div>
          <a className="btn btn-primary" href="/#">Acceder</a>
        </div>
      </div>
    </header>
  );
}

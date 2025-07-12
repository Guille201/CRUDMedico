<template>
    <header class="app-header">
        <h2 class="header-title">Gestión de Citas Médicas</h2>

        <nav class="header-nav">
            <button class="nav-button" @click="$emit('cambiarVista', 'PacientesView')">Introducir Datos</button>
            <button class="nav-button" @click="$emit('cambiarVista', 'ConsultaView')">Consulta</button>
            <button class="nav-button" @click="$emit('cambiarVista', 'TratemientosView')">Tratamientos</button>
            <button class="nav-button" @click="$emit('cambiarVista', 'Visualcomponent')">Usuarios</button>
        </nav>
    </header>
</template>

<script>
export default {
    name: "AppHeader"
};
</script>

<style scoped>
.app-header {
    background-color: #1f2937;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    padding: 16px 24px;
    display: flex;
    align-items: center;
}

/* El título permanece en su lugar */
.header-title {
    font-size: 24px;
    font-weight: 600;
    color: white;
    margin: 0;
    flex-shrink: 0;
}

/* El contenedor de los botones se expande proporcionalmente */
.header-nav {
    display: flex;
    flex-grow: 1;
    justify-content: flex-end;
    gap: 2%;
    margin-left: auto;
    /* Empuja nav hacia el final */
}

/* Botones con ancho proporcional */
.nav-button {
    flex: 1;
    max-width: 150px;
    background-color: #374151;
    color: white;
    padding: 8px 16px;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: background-color 0.2s ease;
    text-align: center;
}

.nav-button:hover {
    background-color: #4b5563;
}
</style>

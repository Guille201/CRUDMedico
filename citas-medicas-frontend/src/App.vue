<template>
  <div id="app">
    <AppHeader @cambiarVista="vistaActiva = $event" />

    <main class="main-content">
      <component :is="vistaActiva" />
    </main>
  </div>
</template>
<script>
import ConsultaView from './components/ConsultaView.vue'
import PacientesView from './components/IntroducirDatos.vue'
import TratemientosView from './components/TratemientosView.vue'
import AppHeader from './components/Header.vue'
import Visualcomponent from './components/PacientesView.vue'

export default {
  name: 'App',
  components: {
    AppHeader,
    Visualcomponent,
    ConsultaView,
    TratemientosView,
    PacientesView
  },data() {
  return {
    vistaActiva: 'Visualcomponent'
  }
}
}
</script>

<style>
.main-content {
  padding: 20px;
}
</style>

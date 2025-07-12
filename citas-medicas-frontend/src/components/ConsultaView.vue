<template>
  <section class="page-wrapper">
    <div class="content-container">
      <div class="table-card">
        <h2 class="table-title">📋 Listado de Consultas</h2>

        <div class="table-wrapper">
          <table class="user-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Paciente</th>
                <th>Primera vez</th>
                <th>Fecha</th>
                <th>Exploración</th>
                <th>Pruebas Extra</th>
                <th>Diagnóstico</th>
                <th>Tratamiento</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(consulta, index) in consultas" :key="index">
                <td>{{ consulta.id }}</td>
                <td>{{ consulta.user?.username }}</td>
                <td>{{ consulta.isFirst ? 'Sí' : 'No' }}</td>
                <td>{{ formatFecha(consulta.fecha) }}</td>
                <td>{{ consulta.exploracion }}</td>
                <td>{{ consulta.pruebasExtra }}</td>
                <td>{{ consulta.juicioClinico }}</td>
                <td>{{ consulta.tratamiento }}</td>
                <td>
                  <button class="btn edit" @click="mostrarFormulario(consulta)">Editar</button>
                  <button class="btn delete" @click="eliminarConsulta(consulta.id)">Eliminar</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <EditarConsulta
        v-if="consultaSeleccionada"
        :consulta-prop="consultaSeleccionada"
        @actualizado="cargarConsultas"
        @cerrar="consultaSeleccionada = null"
      />
    </div>
  </section>
</template>

<script>
import axios from 'axios';
import EditarConsulta from './EditarConsulta.vue';

export default {
  name: 'ConsultasView',
  components: { EditarConsulta },
  data() {
    return {
      consultas: [],
      consultaSeleccionada: null
    };
  },
  mounted() {
    this.cargarConsultas();
  },
  methods: {
    cargarConsultas() {
      axios.get('http://localhost:8080/api/consultas')
        .then(response => {
          this.consultas = response.data;
        })
        .catch(error => {
          console.error('Error al cargar consultas:', error);
        });
    },
    formatFecha(fecha) {
      return new Date(fecha).toLocaleDateString('es-ES');
    },
    mostrarFormulario(consulta) {
      this.consultaSeleccionada = { ...consulta };
    },
    eliminarConsulta(id) {
      if (confirm('¿Estás seguro de eliminar esta consulta?')) {
        axios.delete(`http://localhost:8080/api/consultas/${id}`)
          .then(() => {
            this.consultas = this.consultas.filter(c => c.id !== id);
            alert('Consulta eliminada correctamente.');
          })
          .catch(() => {
            alert('Error al eliminar la consulta.');
          });
      }
    }
  }
};
</script>

<style scoped>

.page-wrapper {
  padding: 40px;
  background-color: #f3f4f6;
  min-height: 100vh;
}

.content-container {
  max-width: 1200px;
  margin: 0 auto;
}

.table-card {
  background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.05);
  border: 1px solid #e5e7eb;
  overflow: hidden;
}

.table-title {
  font-size: 1.5rem;
  font-weight: bold;
  padding: 20px;
  background-color: #e0f2fe;
  color: #0c4a6e;
  border-bottom: 1px solid #cbd5e1;
}

.table-wrapper {
  overflow-x: auto;
}

.user-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 0.95rem;
}

.user-table thead {
  background-color: #f3f4f6;
}

.user-table th,
.user-table td {
  padding: 14px 18px;
  text-align: left;
  border-bottom: 1px solid #e5e7eb;
  white-space: nowrap;
}

.user-table tbody tr:hover {
  background-color: #f0f9ff;
  transition: background 0.2s ease-in-out;
}

.btn {
  padding: 6px 12px;
  border-radius: 6px;
  font-size: 0.875rem;
  font-weight: 500;
  cursor: pointer;
  transition: background 0.2s, color 0.2s;
  border: none;
}

.btn.edit {
  background-color: #dbeafe;
  color: #1d4ed8;
}

.btn.edit:hover {
  background-color: #bfdbfe;
}

.btn.delete {
  background-color: #fee2e2;
  color: #b91c1c;
  margin-left: 6px;
}

.btn.delete:hover {
  background-color: #fecaca;
}
</style>

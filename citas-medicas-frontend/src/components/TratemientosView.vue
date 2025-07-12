<template>
  <section class="page-wrapper">
    <div class="content-container">
      <div class="table-card">
        <h2 class="table-title">💊 Listado de Tratamientos</h2>

        <div class="table-wrapper">
          <table class="user-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Consulta ID</th>
                <th>Dieta</th>
                <th>Ejercicio</th>
                <th>Medicamento</th>
                <th>Estilo de Vida</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(tratamiento, index) in tratamientos"
                :key="index"
              >
                <td>{{ tratamiento.id }}</td>
                <td>{{ tratamiento.consulta?.id }}</td>
                <td>{{ tratamiento.tipoDieta }}</td>
                <td>{{ tratamiento.tipoEjercicio }}</td>
                <td>{{ tratamiento.tipoMedicamento }}</td>
                <td>{{ tratamiento.tipoEstiloVida }}</td>
                <td>
                  <button class="btn edit" @click="iniciarEdicion(tratamiento)">Editar</button>
                  <button class="btn delete" @click="eliminarTratamiento(tratamiento.id)">Eliminar</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- Formulario de edición -->
      <div v-if="tratamientoEditando" class="edit-form-card">
        <h3 class="form-title">✏️ Editar Tratamiento</h3>
        <form @submit.prevent="guardarCambios">
          <div class="form-group">
            <label>Tipo de Dieta</label>
            <input v-model="tratamientoEditando.tipoDieta" type="text" class="input" />
          </div>
          <div class="form-group">
            <label>Tipo de Ejercicio</label>
            <input v-model="tratamientoEditando.tipoEjercicio" type="text" class="input" />
          </div>
          <div class="form-group">
            <label>Tipo de Medicamento</label>
            <input v-model="tratamientoEditando.tipoMedicamento" type="text" class="input" />
          </div>
          <div class="form-group">
            <label>Tipo de Estilo de Vida</label>
            <input v-model="tratamientoEditando.tipoEstiloVida" type="text" class="input" />
          </div>
          <div class="form-actions">
            <button type="submit" class="btn save">Guardar</button>
            <button type="button" class="btn cancel" @click="cancelarEdicion">Cancelar</button>
          </div>
        </form>
      </div>
    </div>
  </section>
</template>

<script>
import axios from 'axios';

export default {
  name: 'TratamientosComponent',
  data() {
    return {
      tratamientos: [],
      tratamientoEditando: null
    };
  },
  mounted() {
    this.cargarTratamientos();
  },
  methods: {
    cargarTratamientos() {
      axios.get('http://localhost:8080/api/tratamientos')
        .then(response => {
          this.tratamientos = response.data;
        })
        .catch(error => {
          console.error('Error al obtener tratamientos:', error);
        });
    },
    iniciarEdicion(tratamiento) {
      this.tratamientoEditando = { ...tratamiento };
    },
    cancelarEdicion() {
      this.tratamientoEditando = null;
    },
    guardarCambios() {
      axios.put(`http://localhost:8080/api/tratamientos/${this.tratamientoEditando.id}`, this.tratamientoEditando)
        .then(() => {
          alert('Tratamiento actualizado con éxito.');
          this.tratamientoEditando = null;
          this.cargarTratamientos();
        })
        .catch(error => {
          console.error('Error al actualizar tratamiento:', error);
          alert('Error al guardar los cambios.');
        });
    },
    eliminarTratamiento(id) {
      if (confirm('¿Estás seguro de que deseas eliminar este tratamiento?')) {
        axios.delete(`http://localhost:8080/api/tratamientos/${id}`)
          .then(() => {
            this.tratamientos = this.tratamientos.filter(t => t.id !== id);
            alert('Tratamiento eliminado.');
          })
          .catch(error => {
            console.error('Error al eliminar tratamiento:', error);
            alert('No se pudo eliminar el tratamiento.');
          });
      }
    }
  }
};
</script>

<style scoped>
/* Layout general */
.page-wrapper {
  padding: 40px;
  background-color: #f3f4f6;
  min-height: 100vh;
}

.content-container {
  max-width: 1200px;
  margin: 0 auto;
}

/* Tarjeta principal */
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

/* Tabla */
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

/* Botones */
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

.btn.save {
  background-color: #10b981;
  color: white;
}

.btn.save:hover {
  background-color: #059669;
}

.btn.cancel {
  background-color: #e5e7eb;
  color: #374151;
}

.btn.cancel:hover {
  background-color: #d1d5db;
}

/* Formulario de edición */
.edit-form-card {
  background-color: #ffffff;
  margin-top: 40px;
  padding: 30px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
}

.form-title {
  font-size: 1.25rem;
  font-weight: bold;
  margin-bottom: 24px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  font-weight: 600;
  margin-bottom: 8px;
  color: #374151;
}

.input {
  width: 100%;
  padding: 10px 12px;
  border: 1px solid #d1d5db;
  border-radius: 6px;
  font-size: 1rem;
  background-color: #f9fafb;
  transition: border-color 0.2s ease;
}

.input:focus {
  border-color: #3b82f6;
  background-color: #fff;
  outline: none;
}

.form-actions {
  display: flex;
  gap: 10px;
  margin-top: 20px;
}
</style>

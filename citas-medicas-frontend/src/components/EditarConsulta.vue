<template>
  <div class="edit-form-card">
    <h3 class="form-title">✏️ Editar Consulta</h3>
    <form @submit.prevent="guardarCambios">
      <div class="form-group">
        <label>Paciente</label>
        <select v-model="consulta.pacienteId" class="input" required @change="actualizarPaciente">
          <option disabled value="">Seleccione un paciente</option>
          <option v-for="paciente in pacientes" :key="paciente.id" :value="paciente.id">
            {{ paciente.username }}
          </option>
        </select>
      </div>

      <div class="form-group">
        <label>Fecha</label>
        <input v-model="consulta.fecha" type="date" class="input" required />
      </div>

      <div class="form-group">
        <label>Síntomas</label>
        <textarea v-model="consulta.sintomas" class="input" required></textarea>
      </div>

      <div class="form-group">
        <label>Exploración</label>
        <textarea v-model="consulta.exploracion" class="input"></textarea>
      </div>

      <div class="form-group">
        <label>Pruebas Extra</label>
        <textarea v-model="consulta.pruebasExtra" class="input"></textarea>
      </div>

      <div class="form-group">
        <label>Diagnóstico</label>
        <textarea v-model="consulta.juicioClinico" class="input" required></textarea>
      </div>

      <div class="form-group">
        <label>Tratamiento</label>
        <textarea v-model="consulta.tratamiento" class="input" required></textarea>
      </div>

      <div class="form-group">
        <label>¿Primera vez?</label>
        <input type="checkbox" v-model="consulta.isFirst" />
      </div>

      <div class="form-actions">
        <button type="submit" class="btn save">Guardar</button>
        <button type="button" class="btn cancel" @click="$emit('cerrar')">Cancelar</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'EditarConsulta',
  props: {
    consultaProp: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      consulta: {
        id: this.consultaProp.id || '',
        pacienteId: this.consultaProp.user?.id || '',
        fecha: this.formatearFechaISO(this.consultaProp.fecha),
        sintomas: this.consultaProp.sintomas || '',
        exploracion: this.consultaProp.exploracion || '',
        pruebasExtra: this.consultaProp.pruebasExtra || '',
        juicioClinico: this.consultaProp.juicioClinico || '',
        tratamiento: this.consultaProp.tratamiento || '',
        isFirst: this.consultaProp.isFirst || false
      },
      pacientes: []
    };
  },
  watch: {
    consultaProp: {
      immediate: true,
      handler(nuevaConsulta) {
        this.consulta = {
          id: nuevaConsulta.id || '',
          pacienteId: nuevaConsulta.user?.id || '',
          fecha: this.formatearFechaISO(nuevaConsulta.fecha),
          sintomas: nuevaConsulta.sintomas || '',
          exploracion: nuevaConsulta.exploracion || '',
          pruebasExtra: nuevaConsulta.pruebasExtra || '',
          juicioClinico: nuevaConsulta.juicioClinico || '',
          tratamiento: nuevaConsulta.tratamiento || '',
          isFirst: nuevaConsulta.isFirst || false
        };
      }
    }
  },
  mounted() {
    this.obtenerPacientes();
  },
  methods: {
    obtenerPacientes() {
      axios
        .get('http://localhost:8080/api/users')
        .then((res) => {
          this.pacientes = res.data.filter((p) => p.role === 'paciente');
        })
        .catch((err) => {
          console.error('Error al obtener pacientes:', err);
        });
    },
    formatearFechaISO(fecha) {
      if (!fecha) return '';
      const d = new Date(fecha);
      return d.toISOString().slice(0, 10);
    },
    actualizarPaciente() {
      // Actualiza el pacienteId cuando se selecciona un nuevo paciente
      this.consulta.pacienteId = this.consulta.pacienteId;
    },
    guardarCambios() {
      const payload = {
        id: this.consulta.id,
        sintomas: this.consulta.sintomas,
        exploracion: this.consulta.exploracion,
        pruebasExtra: this.consulta.pruebasExtra,
        juicioClinico: this.consulta.juicioClinico,
        tratamiento: this.consulta.tratamiento,
        fecha: new Date(this.consulta.fecha).toISOString(),
        isFirst: this.consulta.isFirst,
        user: { id: this.consulta.pacienteId }
      };

      axios
        .put(`http://localhost:8080/api/consultas/${this.consulta.id}`, payload)
        .then(() => {
          alert('Consulta actualizada correctamente.');
          this.$emit('actualizado');
          this.$emit('cerrar');
        })
        .catch((err) => {
          console.error('Error al actualizar consulta:', err);
          alert('Error al actualizar consulta.');
        });
    }
  }
};
</script>

<style scoped>
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

.btn {
  padding: 8px 16px;
  border-radius: 6px;
  font-weight: 600;
  cursor: pointer;
  transition: background-color 0.2s ease;
  border: none;
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
</style>
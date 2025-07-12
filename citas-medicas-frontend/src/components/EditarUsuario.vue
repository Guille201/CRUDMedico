<template>
    <section class="p-6 bg-gray-100 min-h-screen max-w-xl mx-auto">
        <h2 class="text-2xl font-bold mb-6">Editar Usuario</h2>
        <form @submit.prevent="guardarCambios" class="bg-white p-6 rounded shadow">
            <div class="mb-4">
                <label class="block font-semibold mb-1">Nombre de usuario</label>
                <input v-model="usuario.username" type="text" required class="input" />
            </div>
            <div class="mb-4">
                <label class="block font-semibold mb-1">Email</label>
                <input v-model="usuario.email" type="email" required class="input" />
            </div>
            <div class="mb-4">
                <label class="block font-semibold mb-1">DNI</label>
                <input v-model="usuario.dni" type="text" required class="input" />
            </div>
            <div class="mb-4">
                <label class="block font-semibold mb-1">Año de nacimiento</label>
                <input v-model.number="usuario.birthYear" type="number" required min="1900" max="2100" class="input" />
            </div>
            <div class="mb-4">
                <label class="block font-semibold mb-1">Teléfono</label>
                <input v-model="usuario.phone" type="text" class="input" />
            </div>
            <div class="mb-4">
                <label class="block font-semibold mb-1">Dirección</label>
                <input v-model="usuario.address" type="text" class="input" />
            </div>
            <div class="mb-4">
                <label class="block font-semibold mb-1">Rol</label>
                <select v-model="usuario.role" required class="input">
                    <option value="paciente">Paciente</option>
                    <option value="admin">Admin</option>
                </select>
            </div>
            <div class="flex gap-4 mt-6">
                <button type="submit" class="btn save">Guardar</button>
                <button type="button" class="btn cancel" @click="cancelar">Cancelar</button>
            </div>
        </form>
    </section>
</template>

<script>
import axios from 'axios';

export default {
    name: 'EditarUsuario',
    props: {
        usuarioProp: Object
    },
    data() {
        return {
            usuario: { ...this.usuarioProp }
        };
    },
    watch: {
        usuarioProp(nuevo) {
            this.usuario = { ...nuevo };
        }
    },
    methods: {
        guardarCambios() {
            axios.put(`http://localhost:8080/api/users/${this.usuario.id}`, this.usuario)
                .then(() => {
                    alert('Usuario actualizado con éxito');
                    this.$emit('actualizado');
                })
                .catch(() => {
                    alert('Error al actualizar usuario');
                });
        },
        cancelar() {
            this.$emit('cerrar');
        }
    }
};
</script>

<style scoped>
.input {
    width: 100%;
    padding: 8px 10px;
    border: 1px solid #cbd5e1;
    border-radius: 6px;
    font-size: 1rem;
}

.btn {
    padding: 8px 16px;
    border-radius: 6px;
    font-weight: 600;
    cursor: pointer;
    transition: background-color 0.2s ease;
}

.btn.save {
    background-color: #3b82f6;
    color: white;
    border: none;
}

.btn.save:hover {
    background-color: #2563eb;
}

.btn.cancel {
    background-color: #e5e7eb;
    border: none;
}

.btn.cancel:hover {
    background-color: #d1d5db;
}
</style>

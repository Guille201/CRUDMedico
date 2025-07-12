<template>
    <div class="edit-form bg-white p-6 rounded shadow max-w-xl mx-auto mt-8">
        <h3 class="text-xl font-bold mb-4">Editar Tratamiento</h3>
        <form @submit.prevent="emitirGuardar">
            <div class="mb-4">
                <label class="block font-semibold mb-1">Tipo de Dieta</label>
                <input v-model="tratamientoLocal.tipoDieta" type="text" class="input" />
            </div>
            <div class="mb-4">
                <label class="block font-semibold mb-1">Tipo de Ejercicio</label>
                <input v-model="tratamientoLocal.tipoEjercicio" type="text" class="input" />
            </div>
            <div class="mb-4">
                <label class="block font-semibold mb-1">Tipo de Medicamento</label>
                <input v-model="tratamientoLocal.tipoMedicamento" type="text" class="input" />
            </div>
            <div class="mb-4">
                <label class="block font-semibold mb-1">Tipo de Estilo de Vida</label>
                <input v-model="tratamientoLocal.tipoEstiloVida" type="text" class="input" />
            </div>
            <div class="flex gap-4 mt-6">
                <button type="submit" class="btn save">Guardar</button>
                <button type="button" class="btn cancel" @click="$emit('cancelar')">Cancelar</button>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    name: 'EditarTratamiento',
    props: {
        tratamiento: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            tratamientoLocal: { ...this.tratamiento }
        };
    },
    methods: {
        emitirGuardar() {
            this.$emit('guardar', this.tratamientoLocal);
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

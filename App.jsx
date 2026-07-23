import { useState, useEffect } from 'react'
import './App.css'

function App() {
const [prestamos, setPrestamos] = useState([])  
 const [form, setForm] = useState({
    nombre: '',
    monto: '',
    tasa_interes: '',
    plazo_meses: '',
    estado: 'ACTIVO'
})

const handleChange = (e) => {
    setForm({...form, [e.target.name]: e.target.value})
}

const handleSubmit = (e) => {
    e.preventDefault()
    setPrestamos([...prestamos, {...form, id: Date.now()}])
    alert("Préstamo guardado!")
    setForm({nombre: '', monto: '', tasa_interes: '', plazo_meses: '', estado: 'ACTIVO'})
}

return (
    <div className="container">
<h1>💰 Sistema de Gestión de Préstamos</h1>

    <form onSubmit={handleSubmit} className="form">
        <h2>Registrar Préstamo</h2>
        <input name="nombre" placeholder="Nombre del Cliente" value={form.nombre} onChange={handleChange} required />
        <input name="monto" type="number" placeholder="Monto $" value={form.monto} onChange={handleChange} required />
        <input name="tasa_interes" type="number" step="0.01" placeholder="Tasa de Interés %" value={form.tasa_interes} onChange={handleChange} required />
        <input name="plazo_meses" type="number" placeholder="Plazo en Meses" value={form.plazo_meses} onChange={handleChange} required />
        <select name="estado" value={form.estado} onChange={handleChange}>
        <option value="ACTIVO">ACTIVO</option>
        <option value="PAGADO">PAGADO</option>
        <option value="VENCIDO">VENCIDO</option>
        </select>
        <button type="submit">Guardar Préstamo</button>
        </form>

    <h2>Listado de Préstamos</h2>
    <table className="tabla">
        <thead>
    <tr>
            <th>ID</th><th>Nombre</th><th>Monto</th><th>Tasa %</th><th>Plazo</th><th>Estado</th>
    </tr>
        </thead>
        <tbody>
    {prestamos.length === 0 ? 
            <tr><td colSpan="6">No hay préstamos. Registra uno arriba 👆</td></tr> : 
            prestamos.map(p => (
                <tr key={p.id}>
                <td>{p.id}</td><td>{p.nombre}</td><td>${p.monto}</td>
                <td>{p.tasa_interes}%</td><td>{p.plazo_meses} meses</td><td>{p.estado}</td>
                </tr>
        ))}
        </tbody>
    </table>
    </div>
)
}
export default App
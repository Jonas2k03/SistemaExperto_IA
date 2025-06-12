import React, { useState } from 'react';
import axios from 'axios';
import './RecomendadorFlores.css';

const sintomasDisponibles = [
  "impaciencia", "culpa", "soñando con el futuro", "miedo", "intolerancia", "egocentrismo", "odio", "envidia", "celos"
  // ... agrega todos los síntomas que quieras mostrar
];

export default function RecomendadorFlores() {
  const [nombre, setNombre] = useState('');
  const [sintomas, setSintomas] = useState([]);
  const [recomendaciones, setRecomendaciones] = useState([]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await axios.post('http://localhost:8080/api/recomendaciones/obtener', {
      nombre,
      sintomas
    });
    setRecomendaciones(response.data);
  };

  return (
    <div className="pantalla-completa">
      <h1>¡Hola, bienvenido al nuestro <b>Sistema Experto</b>!</h1>
      <p>Dinos tus síntomas y te recomendaremos las flores para sanarte.</p>
      <form className="formulario" onSubmit={handleSubmit}>
        <label>
          <b>Nombre:</b>
          <input
            type="text"
            value={nombre}
            onChange={e => setNombre(e.target.value)}
            required
          />
        </label>
        <label>
          <b>Síntomas:</b>
          <span className="instruccion-multi">(Mantén presionada la tecla Ctrl o Cmd para seleccionar varios)</span>
          <select
            multiple
            value={sintomas}
            onChange={e => setSintomas(Array.from(e.target.selectedOptions, option => option.value))}
            size={Math.min(8, sintomasDisponibles.length)}
          >
            {sintomasDisponibles.map(sintoma => (
              <option key={sintoma} value={sintoma}>{sintoma}</option>
            ))}
          </select>
        </label>
        <button type="submit">Obtener recomendaciones</button>
      </form>
      <hr />
      <h2>Te recomendamos:</h2>
      <div className="recomendaciones-grid">
        {recomendaciones.map((esencia, idx) => (
          <div className="tarjeta-esencia" key={idx}>
            <img
              src={`/imagenes_flores/${esencia.imagen}`}
              alt={esencia.nombre}
              className="imagen-esencia"
            />
            <div className="nombre-esencia">{esencia.nombre}</div>
          </div>
        ))}
      </div>
    </div>
  );
}
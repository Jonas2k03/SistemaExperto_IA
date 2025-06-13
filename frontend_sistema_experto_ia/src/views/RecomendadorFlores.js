import React, { useState } from 'react';
import axios from 'axios';
import './RecomendadorFlores.css';

const sintomasDisponibles = [
  "abatimiento tras contratiempo",
  "abnegacion",
  "abrumado por la responsabilidad",
  "agotamiento",
  "aislado",
  "a la deriva",
  "angustia extrema",
  "ansiedad desconocida",
  "amor egoista",
  "apatia",
  "argumentos mentales",
  "autocompasion",
  "auto-represion",
  "busca opinion",
  "cansancio por anticipacion",
  "celos",
  "conmocion",
  "culpa",
  "derrotismo",
  "desanimo",
  "desatento al presente",
  "desesperanza",
  "dominante",
  "duda de si mismo",
  "egocentrismo",
  "envidia",
  "exceso de entusiasmo",
  "falta de confianza",
  "fatiga mental",
  "histeria",
  "incansable",
  "incapacidad de elegir",
  "incertidumbre sobre direccion en la vida",
  "inflexible",
  "influencias no deseadas",
  "intolerancia",
  "impaciencia",
  "miedo conocido",
  "miedo a perder el control",
  "miedo desconocido",
  "miedo extremo",
  "no aprende de los errores",
  "no sabe decir no",
  "odia su apariencia",
  "odio",
  "pensamientos no deseados",
  "posesivo",
  "preocupacion por seres queridos",
  "preocupacion por si mismo",
  "proteccion al cambio",
  "resentimiento",
  "reservado",
  "resignacion",
  "rigidez",
  "servicial en exceso",
  "shock",
  "sin esperanza",
  "terror",
  "tortura mental",
  "tristeza profunda sin razon",
  "vivir en el pasado"
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
        <div className="sintomas-container-flex">
          <b>Síntomas:</b>
          <div className="sintomas-flex-grid">
            {sintomasDisponibles.map(sintoma => (
              <label key={sintoma} className="checkbox-label-flex">
                <input
                  type="checkbox"
                  value={sintoma}
                  checked={sintomas.includes(sintoma)}
                  onChange={e => {
                    if (e.target.checked) {
                      setSintomas([...sintomas, sintoma]);
                    } else {
                      setSintomas(sintomas.filter(s => s !== sintoma));
                    }
                  }}
                />
                <span>{sintoma}</span>
              </label>
            ))}
          </div>
        </div>
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
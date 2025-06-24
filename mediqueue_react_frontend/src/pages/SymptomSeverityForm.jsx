// src/pages/SymptomSeverityForm.jsx
import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

export default function SymptomSeverityForm() {
  const location = useLocation();
  const navigate = useNavigate();
  const appointment = location.state?.appointment;

  const [severity, setSeverity] = useState({});

  if (!appointment) {
    return <p>No appointment data available.</p>;
  }

  const handleChange = (symptom, value) => {
    setSeverity({ ...severity, [symptom]: value });
  };

  const handleSubmit = async (e) => {
  e.preventDefault();

  try {
    const res = await fetch("http://localhost:8080/api/appointments/severity", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        appointmentId: appointment.id,
        symptomSeverities: severity,
      }),
    });

    if (!res.ok) {
      throw new Error("Failed to submit severity");
    }

    const data = await res.json();
    console.log("Server response:", data);
    alert("Severity submitted successfully!");
     navigate("/view-results", {
        state: {
          finalAppointmentTime: data.finalAppointmentTime,
          message: data.message,
        },
      });
  } catch (error) {
    console.error("Error submitting severity:", error);
    alert("Error submitting severity. Try again.");
  }
};

  return (
    <div className="min-h-screen bg-gray-50 flex justify-center items-center p-6">
      <form
        onSubmit={handleSubmit}
        className="bg-white shadow-xl rounded-lg p-10 w-full max-w-3xl space-y-6"
      >
        <h2 className="text-2xl font-bold text-blue-700 text-center">
          Predicted Disease: {appointment.predictedDisease}
        </h2>
        <p className="text-center text-gray-600">
          Please select severity for the following symptoms:
        </p>

        {appointment.predictedSymptoms.map((symptom) => (
          <div key={symptom} className="flex items-center gap-6">
            <label className="w-40 font-medium text-gray-800">{symptom}</label>
            <select
              required
              className="flex-grow p-2 border border-gray-300 rounded"
              value={severity[symptom] || ""}
              onChange={(e) => handleChange(symptom, e.target.value)}
            >
              <option value="">Select Severity</option>
              <option value="low">Low</option>
              <option value="medium">Medium</option>
              <option value="high">High</option>
            </select>
          </div>
        ))}

        <div className="text-center pt-4">
          <button
            type="submit"
            className="px-6 py-3 bg-blue-700 text-white rounded hover:bg-blue-800"
          >
            Submit Severity
          </button>
        </div>
      </form>
    </div>
  );
}

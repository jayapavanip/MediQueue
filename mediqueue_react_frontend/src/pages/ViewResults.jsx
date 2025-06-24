import { useLocation, useNavigate } from "react-router-dom";

export default function ViewResults() {
  const location = useLocation();
  const navigate = useNavigate();
  const finalAppointmentTime = location.state?.finalAppointmentTime;
  const message = location.state?.message;

  if (!finalAppointmentTime) {
    return (
      <div className="min-h-screen flex justify-center items-center">
        <div className="bg-white p-6 rounded shadow text-center">
          <p className="text-lg text-red-600">No appointment information available.</p>
          <button
            onClick={() => navigate("/")}
            className="mt-4 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
          >
            Go Home
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen flex justify-center items-center bg-green-50 p-6">
      <div className="bg-white rounded-lg shadow-lg p-10 max-w-xl text-center">
        <h2 className="text-2xl font-bold text-green-700 mb-4">Appointment Confirmed!</h2>
        <p className="text-lg text-gray-700 mb-2">
          {message || "Your severity was submitted successfully."}
        </p>
        <p className="text-xl font-semibold text-blue-800 mt-6">
          Your Final Appointment Time:
        </p>
        <p className="text-3xl font-bold text-blue-900 mt-2">{finalAppointmentTime}</p>
        <button
          onClick={() => navigate("/")}
          className="mt-6 px-6 py-3 bg-blue-700 text-white rounded hover:bg-blue-800"
        >
          Return to Home
        </button>
      </div>
    </div>
  );
}

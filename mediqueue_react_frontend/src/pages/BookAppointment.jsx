import { useState } from "react";

const availableSlots = [
  { time: "10:00 AM", isBooked: false },
  { time: "11:00 AM", isBooked: false },
  { time: "12:00 PM", isBooked: false },
  { time: "01:00 PM", isBooked: false },
  { time: "02:00 PM", isBooked: false },
];

export default function BookAppointment() {
  const [fullName, setFullName] = useState("");
  const [gender, setGender] = useState("");
  const [age, setAge] = useState("");
  const [weight, setWeight] = useState("");
  const [height, setHeight] = useState("");
  const [date, setDate] = useState("");
  const [symptoms, setSymptoms] = useState("");
  const [selectedSlot, setSelectedSlot] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      fullName,
      gender,
      age: parseInt(age),
      weight: parseFloat(weight),
      height: parseFloat(height),
      date,
      selectedSlot,
      symptoms,
    };

    try {
      const res = await fetch("http://localhost:8080/api/appointments", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });

      const data = await res.json();
      alert("Appointment saved successfully!");
      console.log("Saved:", data);
      // Optionally reset form or navigate
    } catch (error) {
      console.error("Error:", error);
      alert("Failed to save appointment.");
    }
  };

  return (
    <div className="min-h-screen bg-blue-50 flex justify-center items-center p-6">
      <form onSubmit={handleSubmit} className="bg-white shadow-xl rounded-lg p-10 w-full max-w-4xl space-y-6">
        <h2 className="text-3xl font-bold text-blue-700 text-center mb-4">
          Schedule Your Appointment
        </h2>

        {/* Input Fields */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div className="flex items-center gap-4">
            <label htmlFor="fullName" className="w-28 font-semibold text-gray-700">Full Name:</label>
            <input
              id="fullName"
              type="text"
              value={fullName}
              onChange={(e) => setFullName(e.target.value)}
              className="flex-grow p-2 border border-gray-300 rounded focus:outline-blue-400"
              placeholder="Enter your full name"
            />
          </div>

          <div className="flex items-center gap-4">
            <label htmlFor="gender" className="w-28 font-semibold text-gray-700">Gender:</label>
            <input
              id="gender"
              type="text"
              value={gender}
              onChange={(e) => setGender(e.target.value)}
              className="flex-grow p-2 border border-gray-300 rounded focus:outline-blue-400"
              placeholder="Male/Female"
            />
          </div>

          <div className="flex items-center gap-4">
            <label htmlFor="age" className="w-28 font-semibold text-gray-700">Age:</label>
            <input
              id="age"
              type="number"
              value={age}
              onChange={(e) => setAge(e.target.value)}
              className="flex-grow p-2 border border-gray-300 rounded focus:outline-blue-400"
              placeholder="Your age"
            />
          </div>

          <div className="flex items-center gap-4">
            <label htmlFor="weight" className="w-28 font-semibold text-gray-700">Weight (kg):</label>
            <input
              id="weight"
              type="number"
              value={weight}
              onChange={(e) => setWeight(e.target.value)}
              className="flex-grow p-2 border border-gray-300 rounded focus:outline-blue-400"
              placeholder="e.g. 70"
            />
          </div>

          <div className="flex items-center gap-4">
            <label htmlFor="height" className="w-28 font-semibold text-gray-700">Height (cm):</label>
            <input
              id="height"
              type="number"
              value={height}
              onChange={(e) => setHeight(e.target.value)}
              className="flex-grow p-2 border border-gray-300 rounded focus:outline-blue-400"
              placeholder="e.g. 170"
            />
          </div>

          <div className="flex items-center gap-4">
            <label htmlFor="date" className="w-28 font-semibold text-gray-700">Date:</label>
            <input
              id="date"
              type="date"
              value={date}
              onChange={(e) => setDate(e.target.value)}
              className="flex-grow p-2 border border-gray-300 rounded focus:outline-blue-400"
            />
          </div>
        </div>

        {/* Time Slot */}
        <div>
          <label className="block font-semibold text-gray-700 mb-2">Select Time Slot</label>
          <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-5 gap-4">
            {availableSlots.map((slot, index) => (
              <button
                key={index}
                type="button"
                disabled={slot.isBooked}
                onClick={() => setSelectedSlot(slot.time)}
                className={`px-4 py-2 rounded text-sm font-medium transition ${
                  slot.isBooked
                    ? "bg-red-400 text-white cursor-not-allowed"
                    : selectedSlot === slot.time
                    ? "bg-green-700 text-white"
                    : "bg-green-200 hover:bg-green-400"
                }`}
              >
                {slot.time}
              </button>
            ))}
          </div>
        </div>

        {/* Symptoms */}
        <div>
          <label className="block font-semibold text-gray-700 mb-2">Describe Your Symptoms</label>
          <textarea
            value={symptoms}
            onChange={(e) => setSymptoms(e.target.value)}
            className="w-full p-3 border border-gray-300 rounded focus:outline-blue-400"
            rows="4"
            placeholder="Write your symptoms here..."
          ></textarea>
        </div>

        {/* Submit Button */}
        <div className="text-center pt-4">
          <button
            type="submit"
            className="px-6 py-3 bg-blue-700 text-white rounded hover:bg-blue-800 transition"
          >
            Submit Appointment
          </button>
        </div>
      </form>
    </div>
  );
}

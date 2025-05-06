import bgImage from "../assets/background.png";
import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div
      className="relative min-h-screen bg-cover bg-center"
      style={{ backgroundImage: `url(${bgImage})` }}
    >
      {/* Overlay */}
      <div className="absolute inset-0 bg-black/40 backdrop-blur-sm"></div>

      {/* Content Layer */}
      <div className="relative z-10 flex flex-col justify-between items-center text-center text-white p-6 min-h-screen">

        {/* Main */}
        <div className="flex-grow flex flex-col justify-center items-center">
          <h1 className="text-4xl font-slab font-normal mb-2">WELCOME TO MEDIQUEUE 👨‍⚕️</h1>
          <p className="text-lg mb-2">Your health, our priority — prioritized smarter.</p>
          <p className="mb-8">If you want to book appointments faster...</p>

          <div className="flex flex-col sm:flex-row gap-4">
            <Link to="/book" className="px-6 py-3 border-2 border-white text-white rounded hover:bg-white hover:text-black transition">
                Schedule Your Appointment
            </Link>
            <Link to="/results" className="px-6 py-3 border-2 border-white text-white rounded hover:bg-white hover:text-black transition">
                Check Your Appointment Time Here
            </Link>
          </div>
        </div>

        {/* Footer */}
        <footer className="w-full flex justify-between items-center border-t border-white/30 pt-4 text-sm text-white">
          <div>
            <p>
              Contact us @:{" "}
              <a href="mailto:mediqueue@gmail.com" className="underline">
                mediqueue@gmail.com
              </a>
            </p>
            <p>+1 (123) 456-7890</p>
          </div>
          <div className="text-right">
            <p className="font-bold">MediQueue</p>
            <p className="italic text-white/70">Smart Care Starts Here</p>
          </div>
        </footer>
      </div>
    </div>
  );
}

import { Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import BookAppointment from './pages/BookAppointment';
import SymptomSeverityForm from './pages/SymptomSeverityForm';
import ViewResults from './pages/ViewResults';
import DoctorView from './pages/DoctorView';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/book" element={<BookAppointment />} />
      <Route path="/symptom-severity" element={<SymptomSeverityForm />} />
      <Route path="/results" element={<ViewResults />} />
      <Route path="/doctor" element={<DoctorView />} />
    </Routes>
  );
}

export default App;

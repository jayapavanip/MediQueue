from flask import Flask, request, jsonify
import joblib
import traceback
import json

app = Flask(__name__)

# Load your trained ML model
model = joblib.load("knn_model.pkl")   # Disease predictor
vectorizer = joblib.load("tfidf_vectorizer.pkl") # TF-IDF vectorizer

with open("disease_to_symptoms.json") as f:
    disease_symptom_map = json.load(f)

@app.route('/predict-disease', methods=['POST'])
def predict_disease():
    try:
        data = request.get_json()
        symptoms_text = data['symptoms']  # Example: "fever cough headache"
        transformed = vectorizer.transform([symptoms_text])
        prediction = model.predict(transformed)
        disease = prediction[0]

        # Return predicted disease and its symptom list
        return jsonify({
            'disease': disease,
            'symptoms': disease_symptom_map.get(disease, [])
        })

    except Exception as e:
        return jsonify({'error': str(e), 'trace': traceback.format_exc()}), 500

if __name__ == '__main__':
    app.run(debug=True)

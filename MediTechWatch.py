import random
import time
from datetime import datetime, timedelta

class Accelerometer:
    def get_acceleration(self):
        return (random.uniform(-1, 1), random.uniform(-1, 1), random.uniform(-1, 1))

class HeartRateMonitor:
    def get_heart_rate(self):
        return random.randint(60, 100)

class HypertensionMonitor:
    def get_blood_pressure(self):
        return random.randint(90, 140), random.randint(60, 90)

class SugarDiabetesMonitor:
    def get_glucose_level(self):
        return random.uniform(70, 150)

# Instantiate sensor objects
accelerometer = Accelerometer()
heart_rate_monitor = HeartRateMonitor()
hypertension_monitor = HypertensionMonitor()
sugar_diabetes_monitor = SugarDiabetesMonitor()

#This class reminds patient of his/her appointment
def send_reminder(appointment_time):
    current_time = datetime.now()
    time_until_appointment = appointment_time - current_time
    if time_until_appointment < timedelta(hours=1):
        print("Reminder: You have an appointment with the doctor in an hour!!")

def main():

    # Set an initial appointment time 
    appointment_time = datetime.now() + timedelta(minutes=30)
                                                  
    while True:
        # Get sensor readings
        acceleration = accelerometer.get_acceleration()
        heart_rate = heart_rate_monitor.get_heart_rate()
        systolic_bp, diastolic_bp = hypertension_monitor.get_blood_pressure()
        glucose_level = sugar_diabetes_monitor.get_glucose_level()

        # Display sensor readings on the smartwatch screen
        print("Smartwatch Monitor:")
        print(f"Acceleration: {acceleration}")
        print(f"Heart Rate: {heart_rate} bpm")
        print(f"Blood Pressure: {systolic_bp}/{diastolic_bp} mmHg")
        print(f"Glucose Level: {glucose_level} mg/dL")
        
        # Check for high blood pressure
        if systolic_bp > 130:
            print("High blood pressure detected. Requesting medication from the hospital ASAP.")
        
        # Check for high heart rate
        max_heart_rate = 220 - 30  # Assuming age is 30
        if heart_rate > 0.65 * max_heart_rate:
            print("High heart rate detected. Requesting medication from the hospital ASAP.")
        
        # Check for high glucose level
        if glucose_level > 100:
            print("High glucose level detected. Requesting medication from the hospital ASAP.")
        
        # Send appointment reminder
        send_reminder(appointment_time)


        print("=" * 30)  
        time.sleep(5)  # Delay for 5 seconds before the next reading

if __name__ == "__main__":
    main()

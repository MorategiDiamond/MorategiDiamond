import tkinter as tk
from tkinter import messagebox  
import random
import threading
import time
import cv2 
import numpy as np


class CameraApp:
    def __init__(self):
        self.face_recognized = True  # Initialize to True
        self.object_detected = True

    def run(self):
        threading.Thread(target=self.open_camera).start()

    def open_camera(self):
        cap = cv2.VideoCapture(0)
        
        if not cap.isOpened():
            print("Error: Could not open camera.")
            return
        
        face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')
        
        # Load YOLO model
        net = cv2.dnn.readNet('yolov3.weights', 'yolov3.cfg')
        classes = open('coco.names').read().strip().split('\n')

        while True:
            ret, frame = cap.read()
            
            if not ret:
                print("Error: Could not read frame.")
                break
            
            gray_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
            
            # Detect faces in the frame
            faces = face_cascade.detectMultiScale(gray_frame, scaleFactor=1.1, minNeighbors=5, minSize=(30, 30))
            
            if len(faces) > 0:
                message = "Face recognized, confirmed."
                color = (0, 255, 0)  # Green color for text
                self.face_recognized = True

            else:
              # Detect objects using YOLO
                blob = cv2.dnn.blobFromImage(frame, 1/255.0, (416, 416), swapRB=True, crop=False)
                net.setInput(blob)
                layer_names = net.getUnconnectedOutLayersNames()
                detections = net.forward(layer_names)

                # Check if any objects are detected
                for detection in detections:
                    for obj in detection:
                        scores = obj[5:]
                        class_id = np.argmax(scores)
                        confidence = scores[class_id]


                        if confidence > 0.5:
                            message = f"Object detected: {classes[class_id]}"
                            color = (0, 255, 0)  # Green color for text
                            self.object_detected = True
                            break
                    if self.object_detected:
                        break
                else:
                    message = "Face not recognized, retry." if not self.object_detected else "Object not recognized, retry."
                    color = (0, 0, 255)  # Red color for text

             # Draw text message on the frame
            cv2.putText(frame, message, (10, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, color, 2)

            # Draw rectangles around the detected faces 
            for (x, y, w, h) in faces:
             cv2.rectangle(frame, (x, y), (x + w, y + h), (255, 0, 0), 2)

            cv2.imshow('Camera', frame)

            # Close the camera window
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break
        
        cap.release()
        cv2.destroyAllWindows()  

        if self.face_recognized or self.object_detected:  # Start the drone simulation if a face or object is recognized
            root = tk.Tk()
            app = DroneDeliverySimulation(root)

class DroneDeliverySimulation:
    def __init__(self, root):
        self.root = root
        self.root.title("Medication Drone Delivery Simulation")
        
        self.canvas = tk.Canvas(self.root, width=800, height=600, bg="grey")
        self.canvas.pack()
        
        self.locations = []
        self.create_locations()
        self.camera_view = self.canvas.create_rectangle(0, 0, 800, 600, outline="black")

        self.drone = None
        
        start_button = tk.Button(self.root, text="Start Delivery", command=self.start_delivery)
        start_button.pack()
        
    def create_locations(self):
        num_locations = 2
        for _ in range(num_locations):
            x = random.randint(50, 750)
            y = random.randint(50, 550)
            self.locations.append((x, y))
            self.canvas.create_rectangle(x - 5, y - 5, x + 5, y + 5, fill="blue")
            
    def start_delivery(self):
        if not self.drone:
            self.drone = self.canvas.create_oval(395, 295, 405, 305, fill="red")
            threading.Thread(target=self.move_drone).start()
        
    def move_drone(self):
        for target in self.locations:
            drone_x, drone_y = 400, 300  # Drone's initial position
            target_x, target_y = target
            
            while drone_x != target_x or drone_y != target_y:
                if drone_x < target_x:
                    drone_x += 1
                elif drone_x > target_x:
                    drone_x -= 1
                
                if drone_y < target_y:
                    drone_y += 1
                elif drone_y > target_y:
                    drone_y -= 1
                
                self.canvas.coords(self.drone, drone_x - 5, drone_y - 5, drone_x + 5, drone_y + 5)
                self.update_camera_view(drone_x, drone_y)
                self.root.update()
                time.sleep(0.02)

                
            time.sleep(1)  # Pause at the target location
            
            # Display patient details and deliver medication
            patient = self.get_patient_by_location(target)
            if patient:
                self.deliver_medication(patient)
        
        self.canvas.delete(self.drone)
        self.drone = None
        self.canvas.delete(self.camera_view)
        
    def update_camera_view(self, x, y):
        self.canvas.coords(self.camera_view, x - 100, y - 100, x + 100, y + 100)

    def get_patient_by_location(self, location):
        for patient in patients:
            if patient["location_coordinates"] == location:
                return patient
        return None
        
    def deliver_medication(self, patient):
        drone = Drone("D1") if patient["emergency_alert"] else Drone("D2")
        drone.deliver_medication(
            patient["name"],
            patient["medication"],
            patient["illness"],
            patient["address"],
            patient["emergency_alert"]
        )

# Display patient details using a messagebox
        self.display_patient_details(patient)

    def display_patient_details(self, patient):
        details = (
            f"Patient: {patient['name']}\n"
            f"Emergency alert received from smartwatch for {patient['illness']}\n"
            f"Medication: {patient['medication']}\n"
            f"Illness: {patient['illness']}\n"
            f"Address: {patient['address']}"
        )
        messagebox.showinfo("Patient Details", details)

class Drone:
    def __init__(self, name):
        self.name = name

    def deliver_medication(self, patient, medication, illness, address, emergency_alert=False):
        if emergency_alert:
            print(f"Emergency alert received from smartwatch for {patient} ({illness}).")
        print(f"Drone {self.name} is delivering {medication} to {patient} ({illness}) at {address} ASAP")

        # Simulate drone flying
        self._fly_to_location()

        print(f"Drone {self.name} delivered {medication} to {patient} ({illness}) at {address}")

    def _fly_to_location(self):
        print(f"Drone {self.name} is now flying...")
        time.sleep(1)
        print(f"Drone {self.name} has arrived at the delivery location.")

if __name__ == "__main__":
    root = tk.Tk()

    # Create an instance of CameraApp and run the camera thread
    camera_app = CameraApp()
    camera_app.run()

    # Create an instance of DroneDeliverySimulation
    app = DroneDeliverySimulation(root)

    patients = [
        {
            "name": "John Chauke",
            "medication": "Thiazide Diuretic",
            "illness": "High blood pressure",
            "address": "123 Main St",
            "location_coordinates": app.locations[0],  # Assign the actual coordinates
            "emergency_alert": True
        },
        {
            "name": "Jane Sasa",
            "medication": "Metformin tablets",
            "illness": "Diabetes",
            "address": "456 Elm St",
            "location_coordinates": app.locations[1],  # Assign the actual coordinates
            "emergency_alert": False
        }
    ]

    
    root.mainloop()
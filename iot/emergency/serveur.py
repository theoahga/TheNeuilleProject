import serial
import json
import paho.mqtt.client as mqtt

port = 'COM7'  
baud_rate = 115200
ser = serial.Serial(port, baud_rate, timeout=1)


def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Connected to MQTT broker")
    else:
        print("Connection failed with error code " + str(rc))

def on_publish(client, userdata, mid):
    print("Message published")

def on_message(client, userdata, msg):
    print(f"Received message: {msg.payload.decode()} on topic {msg.topic}")

# Set up the mqtt client
client = mqtt.Client()
client.on_connect = on_connect
client.on_publish = on_publish
client.on_message = on_message

broker_address = "localhost"  
port = 1883
client.connect(broker_address, port, 60)

topic = "emergency/sensor"


try:
    list = "["
    while ser.isOpen():
            if (ser.inWaiting() > 0): 
                data_str = ser.read_until(b'\n').decode('utf-8')
                print(data_str)
                if data_str != "fin\n":
                    if list == "[":
                        list+=data_str
                    else:
                        list+=","+data_str
                else :
                    list+="]"
                    client.publish(topic,  list)
                    print("Publish :")
                    print(list)

                    list = "["


except KeyboardInterrupt:
    ser.close()
    print("Connexion série fermée.")


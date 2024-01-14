from tkinter import *

import serial
import json
import paho.mqtt.client as mqtt
import time

SERIALPORT = "COM8"
BAUDRATE = 115200
client = mqtt.Client()
ser = serial.Serial(SERIALPORT, BAUDRATE)
# json_string = '''
#                 [
#                 {"cid": 1, "i": 1, "t": 2},
#                 {"cid": 2, "i": 1, "t": 2},
#                 {"cid": 3, "i": 1, "t": 2},
#                 {"cid": 4, "i": 1, "t": 2},
#                 {"cid": 5, "i": 1, "t": 2}
#                 ]
#                 '''


def sendUARTMessage(msg):
    ser.write(bytes(msg, 'UTF-8'))

def on_message(client, userdata,msg ):
    data = json.loads(msg.payload.decode())
    print("Sensor state list received:")
    for element in data:
        print(str(element))
        stad = str(element).replace("'","\"").strip()+ '\n'
        sendUARTMessage(stad)
        time.sleep(0.3)
    sendUARTMessage('fin\n')
    time.sleep(1)
    
def setupMQTT():
    client.on_message = on_message
    client.connect("localhost",1883,60)
    client.subscribe("simulation/sensor")  
    try:
        print("Press CTRL+C to exit...")
        client.loop_forever()
    except:
        print("Disconnecting from brocker")

def start():
    print ("Starting Up Serial Monitor")
    setupMQTT()
    
    client.disconnect()
    ser.close()

start()




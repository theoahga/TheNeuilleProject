from tkinter import *

import serial
import json
import paho.mqtt.client as mqtt

SERIALPORT = "COM8"
BAUDRATE = 115200
ser = serial.Serial()
client = mqtt.Client()

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

def setupUART():
    ser = serial.Serial(SERIALPORT, BAUDRATE)
    ser.port=SERIALPORT
    ser.baudrate=BAUDRATE
    ser.bytesize = serial.EIGHTBITS #number of bits per bytes
    ser.parity = serial.PARITY_NONE #set parity check: no parity
    ser.stopbits = serial.STOPBITS_ONE #number of stop bits
    ser.timeout = None          #block read

    # ser.timeout = 0             #non-block read
    # ser.timeout = 2              #timeout block read
    ser.xonxoff = False     #disable software flow control
    ser.rtscts = False     #disable hardware (RTS/CTS) flow control
    ser.dsrdtr = False       #disable hardware (DSR/DTR) flow control
    #ser.writeTimeout = 0     #timeout for write
    print ("Starting Up Serial Monitor")
    try:
            ser.open()
    except serial.SerialException:
            print("Serial {} port not available".format(SERIALPORT))
            exit()

def on_message(client, userdata,msg ):
    data = json.loads(msg.payload.decode())
    print("Sensor state list received:")
    for element in data:
        print(str(element))
        stad = str(element)+ '\n'
        #sendUARTMessage(stad)
    #sendUARTMessage('fin\n')
    
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
    #setupUART()
    setupMQTT()
    
    client.disconnect()
    ser.close()

start()




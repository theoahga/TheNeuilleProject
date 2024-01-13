import serial
import json
import paho.mqtt.client as mqtt

port = 'COM7'  
baud_rate = 115200
#ser = serial.Serial(port, baud_rate, timeout=1)


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

'''
try:
    list = []
    while ser.isOpen():
            if (ser.inWaiting() > 0): 
                data_str = ser.read_until(b'\n').decode('utf-8')
                if data_str != "fin\n":
                    list.append(data_str) 
                else :
                    client.publish(topic, str(list))
                    print("Publish :")
                    print(list)

                    list = []


except KeyboardInterrupt:
    ser.close()
    print("Connexion série fermée.")
'''
ssa= [{"t":0,"i":0,"cid":51},{"t":0,"i":0,"cid":60},{"t":0,"i":0,"cid":50},{"t":0,"i":0,"cid":56},{"t":0,"i":0,"cid":54},{"t":0,"i":0,"cid":48},{"t":0,"i":0,"cid":52},{"t":0,"i":0,"cid":21},{"t":0,"i":0,"cid":17},{"t":0,"i":0,"cid":14},{"t":0,"i":0,"cid":29},{"t":0,"i":0,"cid":37},{"t":0,"i":0,"cid":36},{"t":0,"i":0,"cid":33},{"t":0,"i":0,"cid":32},{"t":0,"i":0,"cid":61},{"t":0,"i":0,"cid":49},{"t":0,"i":0,"cid":53},{"t":0,"i":0,"cid":57},{"t":0,"i":0,"cid":58},{"t":0,"i":0,"cid":47},{"t":0,"i":0,"cid":59},{"t":0,"i":0,"cid":55},{"t":0,"i":0,"cid":13},{"t":0,"i":0,"cid":30},{"t":0,"i":0,"cid":18},{"t":0,"i":0,"cid":15},{"t":0,"i":0,"cid":28},{"t":0,"i":0,"cid":9},{"t":0,"i":0,"cid":5},{"t":0,"i":0,"cid":4},{"t":0,"i":0,"cid":6},{"t":0,"i":0,"cid":7},{"t":0,"i":0,"cid":31},{"t":0,"i":0,"cid":19},{"t":0,"i":0,"cid":23},{"t":0,"i":0,"cid":27},{"t":0,"i":0,"cid":43},{"t":0,"i":0,"cid":39},{"t":0,"i":0,"cid":3},{"t":0,"i":0,"cid":42},{"t":0,"i":0,"cid":11},{"t":0,"i":0,"cid":20},{"t":0,"i":0,"cid":16},{"t":0,"i":0,"cid":41},{"t":0,"i":0,"cid":22},{"t":0,"i":0,"cid":40},{"t":0,"i":0,"cid":46},{"t":0,"i":0,"cid":35},{"t":0,"i":0,"cid":45},{"t":0,"i":0,"cid":34},{"t":0,"i":0,"cid":44},{"t":0,"i":0,"cid":64},{"t":0,"i":0,"cid":80},{"t":3,"i":2,"cid":24},{"t":3,"i":5,"cid":26},{"t":4,"i":4,"cid":8},{"t":1,"i":8,"cid":10},{"t":2,"i":1,"cid":12},{"t":4,"i":5,"cid":25},{"t":4,"i":3,"cid":38},{"t":0,"i":0,"cid":68},{"t":0,"i":0,"cid":77},{"t":0,"i":0,"cid":76},{"t":0,"i":0,"cid":65},{"t":0,"i":0,"cid":72},{"t":0,"i":0,"cid":71},{"t":0,"i":0,"cid":81},{"t":0,"i":0,"cid":69},{"t":0,"i":0,"cid":70},{"t":0,"i":0,"cid":63},{"t":0,"i":0,"cid":75},{"t":0,"i":0,"cid":78},{"t":0,"i":0,"cid":79},{"t":0,"i":0,"cid":66},{"t":0,"i":0,"cid":67},{"t":0,"i":0,"cid":62},{"t":0,"i":0,"cid":74},{"t":0,"i":0,"cid":73},{"t":2,"i":3,"cid":2},{"t":4,"i":6,"cid":1}]
client.publish(topic, str(json.dumps(ssa)))
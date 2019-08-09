#- * -coding: utf - 8 - * -
from flask import Flask
from flask import request
from flask import abort
from flask import Response
from jsonschema import validate
import json

app = Flask(__name__)

@app.route('/')
def hello_world():
  return 'Hello, World!'

@app.route('/hello', methods = ['POST'])
def test_json() :
	payload = request.get_json()
	print(payload)
	# try :
	# 	with open('./payload/payload.schema.json') as f:
	# 		payload_schema = json.loads(f.read())
	# 	validate(instance = payload, schema = payload_schema)
	# 	payload_batch = payload['batch']
	# 	with open('./payload/event.schema.json') as f:
	# 		event_schema = json.loads(f.read())
	# 	for event in payload_batch:
	# 		validate(instance = event, schema = event_schema)
	# 		rl_type = event['rl_message']['rl_type']
	# 		if rl_type == 'identify' :
	# 			with open('./payload/traits.schema.json') as f:
	# 				traits_schema = json.loads(f.read())
	# 			validate(instance=event['rl_message']['rl_context']['rl_traits'], schema=traits_schema)
	# 		else:
	# 			if 'rl_properties' not in event['rl_message'].keys() :
	# 				return "NOT OK", 400
	# 			if 'rl_event' in event['rl_message'].keys():
	# 				rl_event = event['rl_message']['rl_event']
	# 			else :
	# 				rl_event = ''
	# 			schema_name = rl_type + '-' + rl_event.lower().replace(' ', '-') + '.schema.json'
	# 			print(schema_name)
	# 			with open('./payload/'+schema_name) as f:
	# 				property_schema = json.loads(f.read())
	# 			validate(instance = event['rl_message']['rl_properties'], schema = property_schema)
	# except Exception as e:
	# 	print(e)
	# 	return "NOT OK", 400
	return "OK", 200

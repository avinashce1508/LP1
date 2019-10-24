import nltk
import random
import string
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

file = open('investment.txt','r',errors='ignore')
raw = file.read()
raw = raw.lower()
sent_tokens = nltk.sent_tokenize(raw)
word_tokens = nltk.word_tokenize(raw)


lemmer = nltk.stem.WordNetLemmatizer()
def LemTokens(tokens):
    return [lemmer.lemmatize(token) for token in tokens]
	
remove_pun = dict((ord(punc),None) for punc in string.punctuation)
def LemNormalize(text):
	return LemTokens(nltk.word_tokenize(text.lower().translate(remove_pun)))

Greeting_input = ["hello","hi","hey","what's up","sup"]
Greeting_response = ["hello","hi","I am Glad, You are talking to me","hey"]

def greeting(user):
	for word in user.split():
		if word.lower() in Greeting_input:
			return random.choice(Greeting_response)

def response(user):
	robo_response = ''
	tfidfVec = TfidfVectorizer(tokenizer = LemNormalize, stop_words = 'english')
	tfidf = tfidfVec.fit_transform(sent_tokens)
	vals = cosine_similarity(tfidf[-1],tfidf)
	idx = vals.argsort()[0][-2]
	flat = vals.flatten()
	flat.sort()
	req_tfid = flat[-2]
	if(req_tfid==0):
		robo_response += "Sorry, I didn't get you"
		return robo_response
	else:
		robo_response += sent_tokens[idx]
		return robo_response

flag=True;
print("Bot: Hello, I am chatbot. I can answer your queries about Investment. Type 'Bye' to exit");

while flag:
	user = input()
	user = user.lower()
	if(user!="bye"):
	    if(user=="thank you" or user=="thanks"):
	    	print("Bot: Welcome:)")
	    	print("Type 'Bye' to exit")
	    else:
	    	if(greeting(user)!=None):
	    		print("Bot: "+greeting(user));
	    	else:
	    		sent_tokens.append(user)
	    		word_rokens = word_tokens + nltk.word_tokenize(user)
	    		#final_words = list(set(word_tokens))
	    		print("Bot: ",end="")
	    		print(response(user))
	    		sent_tokens.remove(user)
	else:
	    flag= False
	    print("Bye...Take care")			
	

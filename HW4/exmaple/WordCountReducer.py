#! /usr/bin/env python

import sys


def main():
	current_word = None
	current_count = 0
	word = None
	for line in sys.stdin:
		sp = line.strip().split()
		# parse the input we got from mapper.py
    		word, count = line.split('\t', 1)

    		# convert count (currently a string) to int
    		try:
			count = int(count)
    		except ValueError:
        		continue
		if current_word == word:
        		current_count += count
    		else:
			if current_word:
         	   		# write result to STDOUT
            			print '%s\t%s' % (current_word, current_count)
        		current_count = count
        		current_word = word

	# do not forget to output the last word if needed!
	if current_word == word:
    		print '%s\t%s' % (current_word, current_count)

	sys.stdout.flush()

if __name__ == '__main__':
	main()

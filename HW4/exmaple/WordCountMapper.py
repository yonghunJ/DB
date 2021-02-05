#! /usr/bin/env python

import sys
def main():
	for line in sys.stdin:
		sp = line.strip().split()
		for x in sp:
			sys.stdout.write("\t".join((x, str(1), "\n")))
	sys.stdout.flush()

if __name__ == '__main__':
	main()


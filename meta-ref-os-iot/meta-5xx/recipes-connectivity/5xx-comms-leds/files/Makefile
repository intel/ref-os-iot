obj-m += 5xx_comms_leds.o

all:
	$(MAKE) -C $(KBUILD_OUTPUT) M=$(PWD)/ modules

clean:
	$(MAKE) -C $(KBUILD_OUTPUT) M=$(PWD)/ clean

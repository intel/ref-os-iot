#include <linux/init.h>
#include <linux/module.h>
#include <asm/io.h>
#include <linux/timer.h>
#include <linux/device.h>
#include <linux/err.h>
#include <linux/printk.h>
#include <linux/gpio.h>
#include <linux/leds.h>
#include <linux/slab.h>


MODULE_LICENSE("GPL v2");
struct comms_led {
		struct led_classdev cdev;
		unsigned ledpin;
		char name[10];
	};
struct comms_led *ledwifi, *ledbt;
static const struct {
		const char *name;
		const char *trigger;
		const int gpio;
} comms_triggers[] = {
		{ "5xx:bt", "hci0-power", 438},
		{ "5xx:wifi", "phy0radio", 439},
};

static void setgpiovalue(const int gpio_nmbr, bool value)
{
	if (value)
		gpio_set_value(gpio_nmbr, 1);
	else
		gpio_set_value(gpio_nmbr, 0);
}

static void wifi_led_brightness_set(struct led_classdev *cdev, enum led_brightness brightness) {
	if(brightness > 0)
		setgpiovalue(comms_triggers[1].gpio,true);
	else
		setgpiovalue(comms_triggers[1].gpio,false);
}

static void bt_led_brightness_set(struct led_classdev *cdev, enum led_brightness brightness) {
	if(brightness > 0)
		setgpiovalue(comms_triggers[0].gpio,true);
	else
		setgpiovalue(comms_triggers[0].gpio,false);
}

static int comms_register_leds(void)
{
	int err;
	ledwifi = kzalloc(sizeof(*ledwifi), GFP_KERNEL);
	if (ledwifi == NULL)
		return ENOMEM;
	ledbt = kzalloc(sizeof(*ledbt), GFP_KERNEL);
	if (ledbt == NULL)
		return ENOMEM;

	ledwifi->cdev.name = comms_triggers[0].name;
	ledwifi->cdev.brightness_set = bt_led_brightness_set;
	ledwifi->cdev.default_trigger = comms_triggers[0].trigger;

	ledbt->cdev.name = comms_triggers[1].name;
	ledbt->cdev.brightness_set = wifi_led_brightness_set;
	ledbt->cdev.default_trigger = comms_triggers[1].trigger;

	err = led_classdev_register(NULL, &ledbt->cdev);
	if (err) {
			kfree(ledbt);
			return err;
	}

	err = led_classdev_register(NULL, &ledwifi->cdev);
	if (err) {
			kfree(ledwifi);
			return err;
	}
	return 0;
}

static int __init commsleds_init(void)
{
	int ret;
	ledwifi = NULL;
	ledbt = NULL;

	ret = gpio_request(comms_triggers[0].gpio, "LEDBT");
	BUG_ON(ret < 0);

	ret = gpio_direction_output(comms_triggers[0].gpio, 1);
	setgpiovalue(comms_triggers[0].gpio,false);
	BUG_ON(ret < 0);

	ret = gpio_request(comms_triggers[1].gpio, "LEDWIFI");
	BUG_ON(ret < 0);

	ret = gpio_direction_output(comms_triggers[1].gpio, 1);
	setgpiovalue(comms_triggers[1].gpio,false);
	BUG_ON(ret < 0);

	ret = comms_register_leds();
	BUG_ON(ret > 0);

	return 0;
}

static void __exit commsleds_exit(void)
{
	if(ledwifi!=NULL){
		led_classdev_unregister(&ledwifi->cdev);
		kfree(ledwifi);
	}

	if(ledbt!=NULL) {
		led_classdev_unregister(&ledbt->cdev);
		kfree(ledbt);
	}

	gpio_free(comms_triggers[0].gpio);
	gpio_free(comms_triggers[1].gpio);
}
module_init(commsleds_init);
module_exit(commsleds_exit);
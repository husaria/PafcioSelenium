####################################################################################
#                   RUN WITH MAVEN in IntelliJ                                     #
#                                                                                  #
#       Run configuration command                                                  #
#       test -DIS_LOCAL=True -DBROWSER=Firefox                                     #
####################################################################################


#####################################################################################
#Do not forget to put Chrome driver into Your project src/test/resources/drivers !  #
#####################################################################################

Specify env variables or system properties (example):

For Web local run:

	IS_LOCAL=True
	BROWSER=Firefox|Chrome|IE|Safari

For Web remote run:

	IS_REMOTE=True
	BROWSER=Firefox|Chrome|IE|Safari
	EXECUTOR=http://{host}:{port}/wd/hub
	(optional available with Chrome only) MOBILE_DEVICE_EMULATION=Google Nexus 5|Apple iPhone 6|Samsung Galaxy S5

For Web Mobile run:

	IS_MOBILE=True
	PLATFORM=Android|iOS
	BROWSER=Chrome|Safari
	EXECUTOR=http://{host}:{port}/wd/hub
	DEVICE=Device name

For Web Headless run (with PhantomJS without browser):

	IS_HEADLESS=True
	BROWSER=Firefox|Chrome|IE|Safari
	PHANTOM_JS_PATH=C://phantomjs.exe

For Native Mobile run:

	IS_MOBILE=True
	PLATFORM=Android|iOS
	APP={path_to_app}
	EXECUTOR=http://{host}:{port}/wd/hub
	DEVICE=Device name
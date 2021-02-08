module aplikacija {
	exports app;
	exports controller;
	exports helpers;
	exports gui;

	requires specifikacija;
	requires implementacija.custom;
	requires implementacija.json;
	requires implementacija.yaml;
	requires transitive javafx.controls;
		requires javafx.base;
		requires javafx.graphics;
}
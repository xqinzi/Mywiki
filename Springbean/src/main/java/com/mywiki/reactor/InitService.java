package com.mywiki.reactor;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;

public class InitService {
	//
	public static Reactor init() {
		Reactor reactor = Reactors.reactor().env(new Environment()).get();
		return reactor;
	}
}
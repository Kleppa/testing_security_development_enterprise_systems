package org.tsdes.intro.jee.ejb.framework.injection.examples;

import org.tsdes.intro.jee.ejb.framework.injection.InjectorBaseSuite;
import org.tsdes.intro.jee.ejb.framework.injection.Injector;


public class InjectorImp02TestNot extends InjectorBaseSuite {

    @Override
    protected Injector getInjector() {
        return new InjectorImp02();
    }
}
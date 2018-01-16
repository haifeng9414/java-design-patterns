/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iluwatar.abstractfactory.App.FactoryMaker.KingdomType;

/**
 *
 * The Abstract Factory pattern provides a way to encapsulate a group of individual factories that have a common theme
 * without specifying their concrete classes. In normal usage, the client software creates a concrete implementation of
 * the abstract factory and then uses the generic interface of the factory to create the concrete objects that are part
 * of the theme. The client does not know (or care) which concrete objects it gets from each of these internal
 * factories, since it uses only the generic interfaces of their products. This pattern separates the details of
 * implementation of a set of objects from their general usage and relies on object composition, as object creation is
 * implemented in methods exposed in the factory interface.
 * <p>
 * The essence of the Abstract Factory pattern is a factory interface ({@link KingdomFactory}) and its implementations (
 * {@link ElfKingdomFactory}, {@link OrcKingdomFactory}). The example uses both concrete implementations to create a
 * king, a castle and an army.
 *
 */
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    /**
     * The factory of kingdom factories.
     */
    public static class FactoryMaker {

        /**
         * Enumeration for the different types of Kingdoms.
         */
        public enum KingdomType {
            ELF, ORC
        }

        /**
         * The factory method to create KingdomFactory concrete objects.
         */
        public static KingdomFactory makeFactory(KingdomType type) {
            switch (type) {
                case ELF:
                    return new ElfKingdomFactory();
                case ORC:
                    return new OrcKingdomFactory();
                default:
                    throw new IllegalArgumentException("KingdomType not supported.");
            }
        }
    }

    /**
     * Program entry point.
     *
     * @param args
     *          command line args
     */
    public static void main(String[] args) {
        LOGGER.info("Elf Kingdom");
        KingdomFactory elfKingdomFactory = FactoryMaker.makeFactory(KingdomType.ELF);
        LOGGER.info(elfKingdomFactory.createArmy().getDescription());
        LOGGER.info(elfKingdomFactory.createCastle().getDescription());
        LOGGER.info(elfKingdomFactory.createKing().getDescription());

        LOGGER.info("Orc Kingdom");
        KingdomFactory orcKingdomFactory = FactoryMaker.makeFactory(KingdomType.ORC);
        LOGGER.info(orcKingdomFactory.createArmy().getDescription());
        LOGGER.info(orcKingdomFactory.createCastle().getDescription());
        LOGGER.info(orcKingdomFactory.createKing().getDescription());
    }
}
/*
 * Copyright 2010 Henry Coles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.pitest.mutationtest.engine.gregor.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

import org.pitest.functional.FCollection;
import org.pitest.functional.prelude.Prelude;
import org.pitest.help.Help;
import org.pitest.help.PitHelpError;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.mutators.ArgumentPropagationMutator;
import org.pitest.mutationtest.engine.gregor.mutators.BooleanFalseReturnValsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.BooleanTrueReturnValsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.ConditionalsBoundaryMutator;
import org.pitest.mutationtest.engine.gregor.mutators.ConstructorCallMutator;
import org.pitest.mutationtest.engine.gregor.mutators.EmptyObjectReturnValsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.IncrementsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.InlineConstantMutator;
import org.pitest.mutationtest.engine.gregor.mutators.InvertNegsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.MathMutator;
import org.pitest.mutationtest.engine.gregor.mutators.MethodReturnTypeReplacementMutator;
import org.pitest.mutationtest.engine.gregor.mutators.NegateConditionalsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.NonVoidMethodCallMutator;
import org.pitest.mutationtest.engine.gregor.mutators.NullReturnValsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.PrimitiveReturnsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.RemoveConditionalMutator;
import org.pitest.mutationtest.engine.gregor.mutators.RemoveConditionalMutator.Choice;
import org.pitest.mutationtest.engine.gregor.mutators.ReturnValsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.VoidMethodCallMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.NakedReceiverMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.RemoveIncrementsMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.RemoveSwitchMutator;
import org.pitest.mutationtest.engine.gregor.mutators.experimental.SwitchMutator;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator1;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator2;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator3;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator4;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator5;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator6;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator7;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator8;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator9;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator10;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator11;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator12;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator13;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator14;
import org.pitest.mutationtest.engine.gregor.mutators.RelationalOperatorReplacementMutator15;
import org.pitest.mutationtest.engine.gregor.mutators.ArithmeticOperatorDeletionMutator;
import org.pitest.mutationtest.engine.gregor.mutators.ArithmeticOperatorReplacementMutator1;
import org.pitest.mutationtest.engine.gregor.mutators.ArithmeticOperatorReplacementMutator2;
import org.pitest.mutationtest.engine.gregor.mutators.ArithmeticOperatorReplacementMutator3;
import org.pitest.mutationtest.engine.gregor.mutators.ArithmeticOperatorReplacementMutator4;

public final class Mutator {

    private static final Map<String, Iterable<MethodMutatorFactory>> MUTATORS = new LinkedHashMap<>();

    static {

        /**
         * Default mutator that inverts the negation of integer and floating point
         * numbers.
         */
        add("INVERT_NEGS", InvertNegsMutator.INVERT_NEGS_MUTATOR);

        /**
         * Default mutator that mutates the return values of methods.
         */
        add("RETURN_VALS", ReturnValsMutator.RETURN_VALS_MUTATOR);

        /**
         * Optional mutator that mutates integer and floating point inline constants.
         */
        add("INLINE_CONSTS", new InlineConstantMutator());

        /**
         * Default mutator that mutates binary arithmetic operations.
         */
        add("MATH", MathMutator.MATH_MUTATOR);

        /**
         * Default mutator that removes method calls to void methods.
         *
         */
        add("VOID_METHOD_CALLS", VoidMethodCallMutator.VOID_METHOD_CALL_MUTATOR);

        /**
         * Default mutator that negates conditionals.
         */
        add("NEGATE_CONDITIONALS", NegateConditionalsMutator.NEGATE_CONDITIONALS_MUTATOR);

        /**
         * Default mutator that replaces the relational operators with their boundary
         * counterpart.
         */
        add("CONDITIONALS_BOUNDARY", ConditionalsBoundaryMutator.CONDITIONALS_BOUNDARY_MUTATOR);

        /**
         * Default mutator that mutates increments, decrements and assignment increments
         * and decrements of local variables.
         */
        add("INCREMENTS", IncrementsMutator.INCREMENTS_MUTATOR);

        /**
         * NEW mutator that replaces the relational operators with each of the others.
         */
        add("CONDITIONALS_REPLACEMENT1", RelationalOperatorReplacementMutator1.CONDITIONALS_REPLACEMENT_MUTATOR1);
        add("CONDITIONALS_REPLACEMENT2", RelationalOperatorReplacementMutator2.CONDITIONALS_REPLACEMENT_MUTATOR2);
        add("CONDITIONALS_REPLACEMENT3", RelationalOperatorReplacementMutator3.CONDITIONALS_REPLACEMENT_MUTATOR3);
        add("CONDITIONALS_REPLACEMENT4", RelationalOperatorReplacementMutator4.CONDITIONALS_REPLACEMENT_MUTATOR4);
        add("CONDITIONALS_REPLACEMENT5", RelationalOperatorReplacementMutator5.CONDITIONALS_REPLACEMENT_MUTATOR5);
        add("CONDITIONALS_REPLACEMENT6", RelationalOperatorReplacementMutator6.CONDITIONALS_REPLACEMENT_MUTATOR6);
        add("CONDITIONALS_REPLACEMENT7", RelationalOperatorReplacementMutator7.CONDITIONALS_REPLACEMENT_MUTATOR7);
        add("CONDITIONALS_REPLACEMENT8", RelationalOperatorReplacementMutator8.CONDITIONALS_REPLACEMENT_MUTATOR8);
        add("CONDITIONALS_REPLACEMENT9", RelationalOperatorReplacementMutator9.CONDITIONALS_REPLACEMENT_MUTATOR9);
        add("CONDITIONALS_REPLACEMENT10", RelationalOperatorReplacementMutator10.CONDITIONALS_REPLACEMENT_MUTATOR10);
        add("CONDITIONALS_REPLACEMENT11", RelationalOperatorReplacementMutator11.CONDITIONALS_REPLACEMENT_MUTATOR11);
        add("CONDITIONALS_REPLACEMENT12", RelationalOperatorReplacementMutator12.CONDITIONALS_REPLACEMENT_MUTATOR12);
        add("CONDITIONALS_REPLACEMENT13", RelationalOperatorReplacementMutator13.CONDITIONALS_REPLACEMENT_MUTATOR13);
        add("CONDITIONALS_REPLACEMENT14", RelationalOperatorReplacementMutator14.CONDITIONALS_REPLACEMENT_MUTATOR14);
        add("CONDITIONALS_REPLACEMENT15", RelationalOperatorReplacementMutator15.CONDITIONALS_REPLACEMENT_MUTATOR15);

        /**
         * NEW mutator that replaces arithmetic operators with each of the others.
         */
        add("ARITHMETIC_REPLACEMENT1", ArithmeticOperatorReplacementMutator1.ARITHMETIC_REPLACEMENT_MUTATOR1);
        add("ARITHMETIC_REPLACEMENT2", ArithmeticOperatorReplacementMutator2.ARITHMETIC_REPLACEMENT_MUTATOR2);
        add("ARITHMETIC_REPLACEMENT3", ArithmeticOperatorReplacementMutator3.ARITHMETIC_REPLACEMENT_MUTATOR3);
        add("ARITHMETIC_REPLACEMENT4", ArithmeticOperatorReplacementMutator4.ARITHMETIC_REPLACEMENT_MUTATOR4);
        /**
         * NEW mutator that deletes operands in arithmetic expressions with more than
         * one.
         */
        add("ARITHMETIC_DELETION", ArithmeticOperatorDeletionMutator.ARITHMETIC_DELETION_MUTATOR);

        /**
         * NEW mutator that replaces method calls with similar methods that return same type
         * 
         */
        add("RETURN_TYPE_REPLACEMENT", MethodReturnTypeReplacementMutator.RETURN_TYPE_REPLACEMENT);

        /**
         * Optional mutator that removes local variable increments.
         */

        add("REMOVE_INCREMENTS", RemoveIncrementsMutator.REMOVE_INCREMENTS_MUTATOR);

        /**
         * Optional mutator that removes method calls to non void methods.
         */
        add("NON_VOID_METHOD_CALLS", NonVoidMethodCallMutator.NON_VOID_METHOD_CALL_MUTATOR);

        /**
         * Optional mutator that replaces constructor calls with null values.
         */
        add("CONSTRUCTOR_CALLS", ConstructorCallMutator.CONSTRUCTOR_CALL_MUTATOR);

        /**
         * Removes conditional statements so that guarded statements always execute The
         * EQUAL version ignores LT,LE,GT,GE, which is the default behaviour, ORDER
         * version mutates only those.
         */

        add("REMOVE_CONDITIONALS_EQ_IF", new RemoveConditionalMutator(Choice.EQUAL, true));
        add("REMOVE_CONDITIONALS_EQ_ELSE", new RemoveConditionalMutator(Choice.EQUAL, false));
        add("REMOVE_CONDITIONALS_ORD_IF", new RemoveConditionalMutator(Choice.ORDER, true));
        add("REMOVE_CONDITIONALS_ORD_ELSE", new RemoveConditionalMutator(Choice.ORDER, false));
        addGroup("REMOVE_CONDITIONALS", RemoveConditionalMutator.makeMutators());

        add("TRUE_RETURNS", BooleanTrueReturnValsMutator.BOOLEAN_TRUE_RETURN);
        add("FALSE_RETURNS", BooleanFalseReturnValsMutator.BOOLEAN_FALSE_RETURN);
        add("PRIMITIVE_RETURNS", PrimitiveReturnsMutator.PRIMITIVE_RETURN_VALS_MUTATOR);
        add("EMPTY_RETURNS", EmptyObjectReturnValsMutator.EMPTY_RETURN_VALUES);
        add("NULL_RETURNS", NullReturnValsMutator.NULL_RETURN_VALUES);
        addGroup("RETURNS", betterReturns());

        /**
         * Experimental mutator that removed assignments to member variables.
         */
        add("EXPERIMENTAL_MEMBER_VARIABLE",
                new org.pitest.mutationtest.engine.gregor.mutators.experimental.MemberVariableMutator());

        /**
         * Experimental mutator that swaps labels in switch statements
         */
        add("EXPERIMENTAL_SWITCH", new org.pitest.mutationtest.engine.gregor.mutators.experimental.SwitchMutator());

        /**
         * Experimental mutator that replaces method call with one of its parameters of
         * matching type
         */
        add("EXPERIMENTAL_ARGUMENT_PROPAGATION", ArgumentPropagationMutator.ARGUMENT_PROPAGATION_MUTATOR);

        /**
         * Experimental mutator that replaces method call with this
         */
        add("EXPERIMENTAL_NAKED_RECEIVER", NakedReceiverMutator.NAKED_RECEIVER);

        addGroup("REMOVE_SWITCH", RemoveSwitchMutator.makeMutators());
        addGroup("DEFAULTS", defaults());
        addGroup("STRONGER", stronger());
        addGroup("ALL", all());
        addGroup("NEW_DEFAULTS", newDefaults());
    }

    public static Collection<MethodMutatorFactory> all() {
        return fromStrings(MUTATORS.keySet());
    }

    private static Collection<MethodMutatorFactory> stronger() {
        return combine(defaults(), group(new RemoveConditionalMutator(Choice.EQUAL, false), new SwitchMutator()));
    }

    private static Collection<MethodMutatorFactory> combine(Collection<MethodMutatorFactory> a,
            Collection<MethodMutatorFactory> b) {
        final List<MethodMutatorFactory> l = new ArrayList<>(a);
        l.addAll(b);
        return l;
    }

    /**
     * Default set of mutators - designed to provide balance between strength and
     * performance
     */
    public static Collection<MethodMutatorFactory> defaults() {
        return group(InvertNegsMutator.INVERT_NEGS_MUTATOR, ReturnValsMutator.RETURN_VALS_MUTATOR,
                MathMutator.MATH_MUTATOR, VoidMethodCallMutator.VOID_METHOD_CALL_MUTATOR,
                NegateConditionalsMutator.NEGATE_CONDITIONALS_MUTATOR,
                ConditionalsBoundaryMutator.CONDITIONALS_BOUNDARY_MUTATOR, IncrementsMutator.INCREMENTS_MUTATOR);
    }

    /**
     * Proposed new defaults - replaced the RETURN_VALS mutator with the new more
     * stable set
     */
    public static Collection<MethodMutatorFactory> newDefaults() {
        return combine(group(InvertNegsMutator.INVERT_NEGS_MUTATOR, MathMutator.MATH_MUTATOR,
                VoidMethodCallMutator.VOID_METHOD_CALL_MUTATOR, NegateConditionalsMutator.NEGATE_CONDITIONALS_MUTATOR,
                ConditionalsBoundaryMutator.CONDITIONALS_BOUNDARY_MUTATOR, IncrementsMutator.INCREMENTS_MUTATOR),
                betterReturns());
    }

    public static Collection<MethodMutatorFactory> betterReturns() {
        return group(BooleanTrueReturnValsMutator.BOOLEAN_TRUE_RETURN,
                BooleanFalseReturnValsMutator.BOOLEAN_FALSE_RETURN,
                PrimitiveReturnsMutator.PRIMITIVE_RETURN_VALS_MUTATOR, EmptyObjectReturnValsMutator.EMPTY_RETURN_VALUES,
                NullReturnValsMutator.NULL_RETURN_VALUES);
    }

    private static Collection<MethodMutatorFactory> group(final MethodMutatorFactory... ms) {
        return Arrays.asList(ms);
    }

    public static Collection<MethodMutatorFactory> byName(final String name) {
        return FCollection.map(MUTATORS.get(name), Prelude.id(MethodMutatorFactory.class));
    }

    private static void add(final String key, final MethodMutatorFactory value) {
        MUTATORS.put(key, Collections.singleton(value));
    }

    private static void addGroup(final String key, final Iterable<MethodMutatorFactory> value) {
        MUTATORS.put(key, value);
    }

    public static Collection<MethodMutatorFactory> fromStrings(final Collection<String> names) {
        final Set<MethodMutatorFactory> unique = new TreeSet<>(compareId());

        FCollection.flatMapTo(names, fromString(), unique);
        return unique;
    }

    private static Comparator<? super MethodMutatorFactory> compareId() {
        return (o1, o2) -> o1.getGloballyUniqueId().compareTo(o2.getGloballyUniqueId());
    }

    private static Function<String, Iterable<MethodMutatorFactory>> fromString() {
        return a -> {
            final Iterable<MethodMutatorFactory> i = MUTATORS.get(a);
            if (i == null) {
                throw new PitHelpError(Help.UNKNOWN_MUTATOR, a);
            }
            return i;
        };
    }

}

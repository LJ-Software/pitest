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
package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

public enum ArithmeticOperatorReplacementMutator implements MethodMutatorFactory {

    MATH_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
            final MethodVisitor methodVisitor) {
        return new MathMethodVisitor(this, methodInfo, context, methodVisitor);
    }

    @Override
    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }

    @Override
    public String getName() {
        return name();
    }

}

class MathMethodVisitor extends AbstractInsnMutator {

    MathMethodVisitor(final MethodMutatorFactory factory, final MethodInfo methodInfo, final MutationContext context,
            final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
    }

    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<>();

    static {
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.ISUB, "Replaced integer addition with subtraction"));
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IDIV, "Replaced integer addition with division"));
        MUTATIONS.put(Opcodes.IADD,
                new InsnSubstitution(Opcodes.IMUL, "Replaced integer addition with multiplication"));
        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IREM, "Replaced integer addition with modulus"));

        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IADD, "Replaced integer subtraction with addition"));
        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IDIV, "Replaced integer subtraction with division"));
        MUTATIONS.put(Opcodes.ISUB,
                new InsnSubstitution(Opcodes.IMUL, "Replaced integer subtraction with multiplication"));
        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IREM, "Replaced integer subtraction with modulus"));

        MUTATIONS.put(Opcodes.IMUL,
                new InsnSubstitution(Opcodes.IDIV, "Replaced integer multiplication with division"));
        MUTATIONS.put(Opcodes.IMUL,
                new InsnSubstitution(Opcodes.IADD, "Replaced integer multiplication with addition"));
        MUTATIONS.put(Opcodes.IMUL,
                new InsnSubstitution(Opcodes.ISUB, "Replaced integer multiplication with subtraction"));
        MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IREM, "Replaced integer multiplication with modulus"));

        MUTATIONS.put(Opcodes.IDIV,
                new InsnSubstitution(Opcodes.IMUL, "Replaced integer division with multiplication"));
        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.ISUB, "Replaced integer division with subtraction"));
        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IADD, "Replaced integer division with addition"));
        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IREM, "Replaced integer division with modulus"));

        MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IMUL, "Replaced integer modulus with multiplication"));
        MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IDIV, "Replaced integer modulus with division"));
        MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.ISUB, "Replaced integer modulus with subtraction"));
        MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IADD, "Replaced integer modulus with addition"));

        // longs
        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LSUB, "Replaced long addition with subtraction"));
        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LDIV, "Replaced long addition with division"));
        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LMUL, "Replaced long addition with multiplication"));
        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LREM, "Replaced long addition with modulus"));

        MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LADD, "Replaced long subtraction with addition"));
        MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LDIV, "Replaced long subtraction with division"));
        MUTATIONS.put(Opcodes.LSUB,
                new InsnSubstitution(Opcodes.LMUL, "Replaced long subtraction with multiplication"));
        MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LREM, "Replaced long subtraction with modulus"));

        MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LDIV, "Replaced long multiplication with division"));
        MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LADD, "Replaced long multiplication with addition"));
        MUTATIONS.put(Opcodes.LMUL,
                new InsnSubstitution(Opcodes.LSUB, "Replaced long multiplication with subtraction"));
        MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LREM, "Replaced long multiplication with modulus"));

        MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LMUL, "Replaced long division with multiplication"));
        MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LSUB, "Replaced long division with subtraction"));
        MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LADD, "Replaced long division with addition"));
        MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LREM, "Replaced long division with modulus"));

        MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LMUL, "Replaced long modulus with multiplication"));
        MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LDIV, "Replaced long modulus with division"));
        MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LSUB, "Replaced long modulus with subtraction"));
        MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LADD, "Replaced long modulus with addition"));

        // floats
        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FSUB, "Replaced float addition with subtraction"));
        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FDIV, "Replaced float addition with division"));
        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FMUL, "Replaced float addition with multiplication"));
        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FREM, "Replaced float addition with modulus"));

        MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FADD, "Replaced float subtraction with addition"));
        MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FDIV, "Replaced float subtraction with division"));
        MUTATIONS.put(Opcodes.FSUB,
                new InsnSubstitution(Opcodes.FMUL, "Replaced float subtraction with multiplication"));
        MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FREM, "Replaced float subtraction with modulus"));

        MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FDIV, "Replaced float multiplication with division"));
        MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FADD, "Replaced float multiplication with addition"));
        MUTATIONS.put(Opcodes.FMUL,
                new InsnSubstitution(Opcodes.FSUB, "Replaced float multiplication with subtraction"));
        MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FREM, "Replaced float multiplication with modulus"));

        MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FMUL, "Replaced float division with multiplication"));
        MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FSUB, "Replaced float division with subtraction"));
        MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FADD, "Replaced float division with addition"));
        MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FREM, "Replaced float division with modulus"));

        MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FMUL, "Replaced float modulus with multiplication"));
        MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FDIV, "Replaced float modulus with division"));
        MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FSUB, "Replaced float modulus with subtraction"));
        MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FADD, "Replaced float modulus with addition"));

        // doubles
        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DSUB, "Replaced double addition with subtraction"));
        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DDIV, "Replaced double addition with division"));
        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DMUL, "Replaced double addition with multiplication"));
        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DREM, "Replaced double addition with modulus"));

        MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DADD, "Replaced double subtraction with addition"));
        MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DDIV, "Replaced double subtraction with division"));
        MUTATIONS.put(Opcodes.DSUB,
                new InsnSubstitution(Opcodes.DMUL, "Replaced double subtraction with multiplication"));
        MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DREM, "Replaced double subtraction with modulus"));

        MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DDIV, "Replaced double multiplication with division"));
        MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DADD, "Replaced double multiplication with addition"));
        MUTATIONS.put(Opcodes.DMUL,
                new InsnSubstitution(Opcodes.DSUB, "Replaced double multiplication with subtraction"));
        MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DREM, "Replaced double multiplication with modulus"));

        MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DMUL, "Replaced double division with multiplication"));
        MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DSUB, "Replaced double division with subtraction"));
        MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DADD, "Replaced double division with addition"));
        MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DREM, "Replaced double division with modulus"));

        MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DMUL, "Replaced double modulus with multiplication"));
        MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DDIV, "Replaced double modulus with division"));
        MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DSUB, "Replaced double modulus with subtraction"));
        MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DADD, "Replaced double modulus with addition"));

    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }

}
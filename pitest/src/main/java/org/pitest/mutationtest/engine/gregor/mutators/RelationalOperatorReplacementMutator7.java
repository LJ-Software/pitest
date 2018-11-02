package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;

public enum RelationalOperatorReplacementMutator7 implements MethodMutatorFactory {

        CONDITIONALS_REPLACEMENT_MUTATOR7;

        @Override
        public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
                        final MethodVisitor methodVisitor) {
                return new ConditionalsBoundaryMethodVisitor(this, context, methodVisitor);
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

class RelationalReplacementMethodVisitor7 extends AbstractJumpMutator {

        RelationalReplacementMethodVisitor7(final MethodMutatorFactory factory, final MutationContext context,
                        final MethodVisitor writer) {
                super(factory, context, writer);
        }

        private static final String DESCRIPTION = "Replaced if ints are equal with if ints are not equal";
        private static final Map<Integer, Substitution> MUTATIONS = new HashMap<>();

        static {
                MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if ints are equal with if references are not equal"));
                MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if ints are not equal with if references are not equal"));
                MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if value1 is less than value2 with if references are not equal"));
                MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if value1 is greater than or equal to value2 with if references are not equal"));
                MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if value1 is greater than value2 with if references are not equal"));
                MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if value1 is less than or equal to value2 with if references are not equal"));
                MUTATIONS.put(Opcodes.IF_ACMPEQ, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if references are equal with if references are not equal"));
                MUTATIONS.put(Opcodes.IF_ACMPNE, new Substitution(Opcodes.IF_ICMPEQ,
                                "Replaced if references are not equal with if ints are equal"));
                MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if value is 0 with if references are not equal"));
                MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if value is not 0 with if references are not equal"));
                MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if value is less than 0 with if references are not equal"));
                MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if value is greater than or equal to 0 with if references are not equal"));
                MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if value is less than or equal to 0 with if references are not equal"));
                MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if value is greater than 0 with if references are not equal"));
                MUTATIONS.put(Opcodes.IFNULL, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if value is null with if references are not equal"));
                MUTATIONS.put(Opcodes.IFNONNULL, new Substitution(Opcodes.IF_ACMPNE,
                                "Replaced if value is not null with if references are not equal"));
        }

        @Override
        protected Map<Integer, Substitution> getMutations() {
                return MUTATIONS;
        }

}
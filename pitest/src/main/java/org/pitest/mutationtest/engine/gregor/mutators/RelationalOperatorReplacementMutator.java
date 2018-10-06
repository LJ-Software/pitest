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

public enum RelationalOperatorReplacementMutator implements MethodMutatorFactory {

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
        MUTATIONS.put(Opcodes.IF_ICMPEQ,
                new InsnSubstitution(Opcodes.IF_ICMPNE, "Replaced if ints are equal with if ints are not equal"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if ints are equal with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if ints are equal with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if ints are equal with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if ints are equal with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ,
                new InsnSubstitution(Opcodes.IF_ACMPEQ, "Replaced if ints are equal with if references are equal"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ,
                new InsnSubstitution(Opcodes.IF_ACMPNE, "Replaced if ints are equal with if references are not equal"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ,
                new InsnSubstitution(Opcodes.IFEQ, "Replaced if ints are equal with if value is 0"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ,
                new InsnSubstitution(Opcodes.IFNE, "Replaced if ints are equal with if value is not 0"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ,
                new InsnSubstitution(Opcodes.IFLT, "Replaced if ints are equal with if value is less than 0"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if ints are equal with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if ints are equal with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ,
                new InsnSubstitution(Opcodes.IFGT, "Replaced if ints are equal with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ,
                new InsnSubstitution(Opcodes.IFNULL, "Replaced if ints are equal with if value is null"));
        MUTATIONS.put(Opcodes.IF_ICMPEQ,
                new InsnSubstitution(Opcodes.IFNONNULL, "Replaced if ints are equal with if value is not null"));

        MUTATIONS.put(Opcodes.IF_ICMPNE,
                new InsnSubstitution(Opcodes.IF_ICMPEQ, "Replaced if ints are not equal with if ints are equal"));
        MUTATIONS.put(Opcodes.IF_ICMPNE, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if ints are not equal with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IF_ICMPNE, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if ints are not equal with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ICMPNE, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if ints are not equal with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IF_ICMPNE, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if ints are not equal with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ICMPNE,
                new InsnSubstitution(Opcodes.IF_ACMPEQ, "Replaced if ints are not equal with if references are equal"));
        MUTATIONS.put(Opcodes.IF_ICMPNE, new InsnSubstitution(Opcodes.IF_ACMPNE,
                "Replaced if ints are not equal with if references are not equal"));
        MUTATIONS.put(Opcodes.IF_ICMPNE,
                new InsnSubstitution(Opcodes.IFEQ, "Replaced if ints are not equal with if value is 0"));
        MUTATIONS.put(Opcodes.IF_ICMPNE,
                new InsnSubstitution(Opcodes.IFNE, "Replaced if ints are not equal with if value is not 0"));
        MUTATIONS.put(Opcodes.IF_ICMPNE,
                new InsnSubstitution(Opcodes.IFLT, "Replaced if ints are not equal with if value is less than 0"));
        MUTATIONS.put(Opcodes.IF_ICMPNE, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if ints are not equal with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ICMPNE, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if ints are not equal with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ICMPNE,
                new InsnSubstitution(Opcodes.IFGT, "Replaced if ints are not equal with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IF_ICMPNE,
                new InsnSubstitution(Opcodes.IFNULL, "Replaced if ints are not equal with if value is null"));
        MUTATIONS.put(Opcodes.IF_ICMPNE,
                new InsnSubstitution(Opcodes.IFNONNULL, "Replaced if ints are not equal with if value is not null"));

        MUTATIONS.put(Opcodes.IF_ICMPLT, new InsnSubstitution(Opcodes.IF_ICMPNE,
                "Replaced if value1 is less than value2 with if ints are not equal"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new InsnSubstitution(Opcodes.IF_ICMPEQ,
                "Replaced if value1 is less than value2 with if ints are equal"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if value1 is less than value2 with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if value1 is less than value2 with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if value1 is less than value2 with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new InsnSubstitution(Opcodes.IF_ACMPEQ,
                "Replaced if value1 is less than value2 with if references are equal"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new InsnSubstitution(Opcodes.IF_ACMPNE,
                "Replaced if value1 is less than value2 with if references are not equal"));
        MUTATIONS.put(Opcodes.IF_ICMPLT,
                new InsnSubstitution(Opcodes.IFEQ, "Replaced if value1 is less than value2 with if value is 0"));
        MUTATIONS.put(Opcodes.IF_ICMPLT,
                new InsnSubstitution(Opcodes.IFNE, "Replaced if value1 is less than value2 with if value is not 0"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new InsnSubstitution(Opcodes.IFLT,
                "Replaced if value1 is less than value2 with if value is less than 0"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if value1 is less than value2 with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if value1 is less than value2 with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new InsnSubstitution(Opcodes.IFGT,
                "Replaced if value1 is less than value2 with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IF_ICMPLT,
                new InsnSubstitution(Opcodes.IFNULL, "Replaced if value1 is less than value2 with if value is null"));
        MUTATIONS.put(Opcodes.IF_ICMPLT, new InsnSubstitution(Opcodes.IFNONNULL,
                "Replaced if value1 is less than value2 with if value is not null"));

        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IF_ICMPNE,
                "Replaced if value1 is greater than or equal to value2 with if ints are not equal"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if value1 is greater than or equal to value2 with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IF_ICMPEQ,
                "Replaced if value1 is greater than or equal to value2 with if ints are equal"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if value1 is greater than or equal to value2 with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if value1 is greater than or equal to value2 with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IF_ACMPEQ,
                "Replaced if value1 is greater than or equal to value2 with if references are equal"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IF_ACMPNE,
                "Replaced if value1 is greater than or equal to value2 with if references are not equal"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IFEQ,
                "Replaced if value1 is greater than or equal to value2 with if value is 0"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IFNE,
                "Replaced if value1 is greater than or equal to value2 with if value is not 0"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IFLT,
                "Replaced if value1 is greater than or equal to value2 with if value is less than 0"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if value1 is greater than or equal to value2 with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if value1 is greater than or equal to value2 with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IFGT,
                "Replaced if value1 is greater than or equal to value2 with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IFNULL,
                "Replaced if value1 is greater than or equal to value2 with if value is null"));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new InsnSubstitution(Opcodes.IFNONNULL,
                "Replaced if value1 is greater than or equal to value2 with if value is not null"));

        MUTATIONS.put(Opcodes.IF_ICMPGT, new InsnSubstitution(Opcodes.IF_ICMPNE,
                "Replaced if value1 is greater than value2 with if ints are not equal"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if value1 is greater than value2 with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if value1 is greater than value2 with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new InsnSubstitution(Opcodes.IF_ICMPEQ,
                "Replaced if value1 is greater than value2 with if ints are equal"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if value1 is greater than value2 with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new InsnSubstitution(Opcodes.IF_ACMPEQ,
                "Replaced if value1 is greater than value2 with if references are equal"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new InsnSubstitution(Opcodes.IF_ACMPNE,
                "Replaced if value1 is greater than value2 with if references are not equal"));
        MUTATIONS.put(Opcodes.IF_ICMPGT,
                new InsnSubstitution(Opcodes.IFEQ, "Replaced if value1 is greater than value2 with if value is 0"));
        MUTATIONS.put(Opcodes.IF_ICMPGT,
                new InsnSubstitution(Opcodes.IFNE, "Replaced if value1 is greater than value2 with if value is not 0"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new InsnSubstitution(Opcodes.IFLT,
                "Replaced if value1 is greater than value2 with if value is less than 0"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if value1 is greater than value2 with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if value1 is greater than value2 with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new InsnSubstitution(Opcodes.IFGT,
                "Replaced if value1 is greater than value2 with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new InsnSubstitution(Opcodes.IFNULL,
                "Replaced if value1 is greater than value2 with if value is null"));
        MUTATIONS.put(Opcodes.IF_ICMPGT, new InsnSubstitution(Opcodes.IFNONNULL,
                "Replaced if value1 is greater than value2 with if value is not null"));

        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IF_ICMPNE,
                "Replaced if value1 is less than or equal to value2 with if ints are not equal"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if value1 is less than or equal to value2 with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if value1 is less than or equal to value2 with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if value1 is less than or equal to value2 with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IF_ICMPEQ,
                "Replaced if value1 is less than or equal to value2 with if ints are equal"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IF_ACMPEQ,
                "Replaced if value1 is less than or equal to value2 with if references are equal"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IF_ACMPNE,
                "Replaced if value1 is less than or equal to value2 with if references are not equal"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IFEQ,
                "Replaced if value1 is less than or equal to value2 with if value is 0"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IFNE,
                "Replaced if value1 is less than or equal to value2 with if value is not 0"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IFLT,
                "Replaced if value1 is less than or equal to value2 with if value is less than 0"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if value1 is less than or equal to value2 with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if value1 is less than or equal to value2 with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IFGT,
                "Replaced if value1 is less than or equal to value2 with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IFNULL,
                "Replaced if value1 is less than or equal to value2 with if value is null"));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new InsnSubstitution(Opcodes.IFNONNULL,
                "Replaced if value1 is less than or equal to value2 with if value is not null"));

        MUTATIONS.put(Opcodes.IF_ACMPEQ,
                new InsnSubstitution(Opcodes.IF_ICMPNE, "Replaced if references are equal with if ints are not equal"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if references are equal with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if references are equal with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if references are equal with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if references are equal with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ,
                new InsnSubstitution(Opcodes.IF_ICMPEQ, "Replaced if references are equal with if ints are equal"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ, new InsnSubstitution(Opcodes.IF_ACMPNE,
                "Replaced if references are equal with if references are not equal"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ,
                new InsnSubstitution(Opcodes.IFEQ, "Replaced if references are equal with if value is 0"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ,
                new InsnSubstitution(Opcodes.IFNE, "Replaced if references are equal with if value is not 0"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ,
                new InsnSubstitution(Opcodes.IFLT, "Replaced if references are equal with if value is less than 0"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if references are equal with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if references are equal with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ,
                new InsnSubstitution(Opcodes.IFGT, "Replaced if references are equal with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ,
                new InsnSubstitution(Opcodes.IFNULL, "Replaced if references are equal with if value is null"));
        MUTATIONS.put(Opcodes.IF_ACMPEQ,
                new InsnSubstitution(Opcodes.IFNONNULL, "Replaced if references are equal with if value is not null"));

        MUTATIONS.put(Opcodes.IF_ACMPNE, new InsnSubstitution(Opcodes.IF_ICMPNE,
                "Replaced if references are not equal with if ints are not equal"));
        MUTATIONS.put(Opcodes.IF_ACMPNE, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if references are not equal with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IF_ACMPNE, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if references are not equal with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ACMPNE, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if references are not equal with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IF_ACMPNE, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if references are not equal with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IF_ACMPNE, new InsnSubstitution(Opcodes.IF_ACMPEQ,
                "Replaced if references are not equal with if references are equal"));
        MUTATIONS.put(Opcodes.IF_ACMPNE,
                new InsnSubstitution(Opcodes.IF_ICMPEQ, "Replaced if references are not equal with if ints are equal"));
        MUTATIONS.put(Opcodes.IF_ACMPNE,
                new InsnSubstitution(Opcodes.IFEQ, "Replaced if references are not equal with if value is 0"));
        MUTATIONS.put(Opcodes.IF_ACMPNE,
                new InsnSubstitution(Opcodes.IFNE, "Replaced if references are not equal with if value is not 0"));
        MUTATIONS.put(Opcodes.IF_ACMPNE, new InsnSubstitution(Opcodes.IFLT,
                "Replaced if references are not equal with if value is less than 0"));
        MUTATIONS.put(Opcodes.IF_ACMPNE, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if references are not equal with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ACMPNE, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if references are not equal with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IF_ACMPNE, new InsnSubstitution(Opcodes.IFGT,
                "Replaced if references are not equal with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IF_ACMPNE,
                new InsnSubstitution(Opcodes.IFNULL, "Replaced if references are not equal with if value is null"));
        MUTATIONS.put(Opcodes.IF_ACMPNE, new InsnSubstitution(Opcodes.IFNONNULL,
                "Replaced if references are not equal with if value is not null"));

        MUTATIONS.put(Opcodes.IFEQ,
                new InsnSubstitution(Opcodes.IF_ICMPNE, "Replaced if value is 0 with if ints are not equal"));
        MUTATIONS.put(Opcodes.IFEQ,
                new InsnSubstitution(Opcodes.IF_ICMPLT, "Replaced if value is 0 with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IFEQ, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if value is 0 with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IFEQ, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if value is 0 with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IFEQ, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if value is 0 with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IFEQ,
                new InsnSubstitution(Opcodes.IF_ACMPEQ, "Replaced if value is 0 with if references are equal"));
        MUTATIONS.put(Opcodes.IFEQ,
                new InsnSubstitution(Opcodes.IF_ACMPNE, "Replaced if value is 0 with if references are not equal"));
        MUTATIONS.put(Opcodes.IFEQ,
                new InsnSubstitution(Opcodes.IF_ICMPEQ, "Replaced if value is 0 with if ints are equal"));
        MUTATIONS.put(Opcodes.IFEQ,
                new InsnSubstitution(Opcodes.IFNE, "Replaced if value is 0 with if value is not 0"));
        MUTATIONS.put(Opcodes.IFEQ,
                new InsnSubstitution(Opcodes.IFLT, "Replaced if value is 0 with if value is less than 0"));
        MUTATIONS.put(Opcodes.IFEQ, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if value is 0 with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IFEQ,
                new InsnSubstitution(Opcodes.IFLE, "Replaced if value is 0 with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IFEQ,
                new InsnSubstitution(Opcodes.IFGT, "Replaced if value is 0 with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IFEQ,
                new InsnSubstitution(Opcodes.IFNULL, "Replaced if value is 0 with if value is null"));
        MUTATIONS.put(Opcodes.IFEQ,
                new InsnSubstitution(Opcodes.IFNONNULL, "Replaced if value is 0 with if value is not null"));

        MUTATIONS.put(Opcodes.IFNE,
                new InsnSubstitution(Opcodes.IF_ICMPNE, "Replaced if value is not 0 with if ints are not equal"));
        MUTATIONS.put(Opcodes.IFNE, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if value is not 0 with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IFNE, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if value is not 0 with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IFNE, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if value is not 0 with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IFNE, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if value is not 0 with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IFNE,
                new InsnSubstitution(Opcodes.IF_ACMPEQ, "Replaced if value is not 0 with if references are equal"));
        MUTATIONS.put(Opcodes.IFNE,
                new InsnSubstitution(Opcodes.IF_ACMPNE, "Replaced if value is not 0 with if references are not equal"));
        MUTATIONS.put(Opcodes.IFNE,
                new InsnSubstitution(Opcodes.IFEQ, "Replaced if value is not 0 with if value is 0"));
        MUTATIONS.put(Opcodes.IFNE,
                new InsnSubstitution(Opcodes.IF_ICMPEQ, "Replaced if value is not 0 with if ints are equal"));
        MUTATIONS.put(Opcodes.IFNE,
                new InsnSubstitution(Opcodes.IFLT, "Replaced if value is not 0 with if value is less than 0"));
        MUTATIONS.put(Opcodes.IFNE, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if value is not 0 with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IFNE, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if value is not 0 with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IFNE,
                new InsnSubstitution(Opcodes.IFGT, "Replaced if value is not 0 with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IFNE,
                new InsnSubstitution(Opcodes.IFNULL, "Replaced if value is not 0 with if value is null"));
        MUTATIONS.put(Opcodes.IFNE,
                new InsnSubstitution(Opcodes.IFNONNULL, "Replaced if value is not 0 with if value is not null"));

        MUTATIONS.put(Opcodes.IFLT,
                new InsnSubstitution(Opcodes.IF_ICMPNE, "Replaced if value is less than 0 with if ints are not equal"));
        MUTATIONS.put(Opcodes.IFLT, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if value is less than 0 with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IFLT, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if value is less than 0 with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IFLT, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if value is less than 0 with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IFLT, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if value is less than 0 with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IFLT, new InsnSubstitution(Opcodes.IF_ACMPEQ,
                "Replaced if value is less than 0 with if references are equal"));
        MUTATIONS.put(Opcodes.IFLT, new InsnSubstitution(Opcodes.IF_ACMPNE,
                "Replaced if value is less than 0 with if references are not equal"));
        MUTATIONS.put(Opcodes.IFLT,
                new InsnSubstitution(Opcodes.IFEQ, "Replaced if value is less than 0 with if value is 0"));
        MUTATIONS.put(Opcodes.IFLT,
                new InsnSubstitution(Opcodes.IFNE, "Replaced if value is less than 0 with if value is not 0"));
        MUTATIONS.put(Opcodes.IFLT,
                new InsnSubstitution(Opcodes.IF_ICMPEQ, "Replaced if value is less than 0 with if ints are equal"));
        MUTATIONS.put(Opcodes.IFLT, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if value is less than 0 with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IFLT, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if value is less than 0 with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IFLT,
                new InsnSubstitution(Opcodes.IFGT, "Replaced if value is less than 0 with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IFLT,
                new InsnSubstitution(Opcodes.IFNULL, "Replaced if value is less than 0 with if value is null"));
        MUTATIONS.put(Opcodes.IFLT,
                new InsnSubstitution(Opcodes.IFNONNULL, "Replaced if value is less than 0 with if value is not null"));

        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IF_ICMPNE,
                "Replaced if value is greater than or equal to 0 with if ints are not equal"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if value is greater than or equal to 0 with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if value is greater than or equal to 0 with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if value is greater than or equal to 0 with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if value is greater than or equal to 0 with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IF_ACMPEQ,
                "Replaced if value is greater than or equal to 0 with if references are equal"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IF_ACMPNE,
                "Replaced if value is greater than or equal to 0 with if references are not equal"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IFEQ,
                "Replaced if value is greater than or equal to 0 with if value is 0"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IFNE,
                "Replaced if value is greater than or equal to 0 with if value is not 0"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IFLT,
                "Replaced if value is greater than or equal to 0 with if value is less than 0"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IF_ICMPEQ,
                "Replaced if value is greater than or equal to 0 with if ints are equal"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if value is greater than or equal to 0 with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IFGT,
                "Replaced if value is greater than or equal to 0 with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IFNULL,
                "Replaced if value is greater than or equal to 0 with if value is null"));
        MUTATIONS.put(Opcodes.IFGE, new InsnSubstitution(Opcodes.IFNONNULL,
                "Replaced if value is greater than or equal to 0 with if value is not null"));

        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IF_ICMPNE,
                "Replaced if value is less than or equal to 0 with if ints are not equal"));
        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if value is less than or equal to 0 with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if value is less than or equal to 0 with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if value is less than or equal to 0 with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if value is less than or equal to 0 with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IF_ACMPEQ,
                "Replaced if value is less than or equal to 0 with if references are equal"));
        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IF_ACMPNE,
                "Replaced if value is less than or equal to 0 with if references are not equal"));
        MUTATIONS.put(Opcodes.IFLE,
                new InsnSubstitution(Opcodes.IFEQ, "Replaced if value is less than or equal to 0 with if value is 0"));
        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IFNE,
                "Replaced if value is less than or equal to 0 with if value is not 0"));
        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IFLT,
                "Replaced if value is less than or equal to 0 with if value is less than 0"));
        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if value is less than or equal to 0 with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IF_ICMPEQ,
                "Replaced if value is less than or equal to 0 with if ints are equal"));
        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IFGT,
                "Replaced if value is less than or equal to 0 with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IFNULL,
                "Replaced if value is less than or equal to 0 with if value is null"));
        MUTATIONS.put(Opcodes.IFLE, new InsnSubstitution(Opcodes.IFNONNULL,
                "Replaced if value is less than or equal to 0 with if value is not null"));

        MUTATIONS.put(Opcodes.IFGT, new InsnSubstitution(Opcodes.IF_ICMPNE,
                "Replaced if value is greater than 0 with if ints are not equal"));
        MUTATIONS.put(Opcodes.IFGT, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if value is greater than 0 with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IFGT, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if value is greater than 0 with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IFGT, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if value is greater than 0 with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IFGT, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if value is greater than 0 with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IFGT, new InsnSubstitution(Opcodes.IF_ACMPEQ,
                "Replaced if value is greater than 0 with if references are equal"));
        MUTATIONS.put(Opcodes.IFGT, new InsnSubstitution(Opcodes.IF_ACMPNE,
                "Replaced if value is greater than 0 with if references are not equal"));
        MUTATIONS.put(Opcodes.IFGT,
                new InsnSubstitution(Opcodes.IFEQ, "Replaced if value is greater than 0 with if value is 0"));
        MUTATIONS.put(Opcodes.IFGT,
                new InsnSubstitution(Opcodes.IFNE, "Replaced if value is greater than 0 with if value is not 0"));
        MUTATIONS.put(Opcodes.IFGT,
                new InsnSubstitution(Opcodes.IFLT, "Replaced if value is greater than 0 with if value is less than 0"));
        MUTATIONS.put(Opcodes.IFGT, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if value is greater than 0 with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IFGT, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if value is greater than 0 with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IFGT,
                new InsnSubstitution(Opcodes.IF_ICMPEQ, "Replaced if value is greater than 0 with if ints are equal"));
        MUTATIONS.put(Opcodes.IFGT,
                new InsnSubstitution(Opcodes.IFNULL, "Replaced if value is greater than 0 with if value is null"));
        MUTATIONS.put(Opcodes.IFGT, new InsnSubstitution(Opcodes.IFNONNULL,
                "Replaced if value is greater than 0 with if value is not null"));

        MUTATIONS.put(Opcodes.IFNULL,
                new InsnSubstitution(Opcodes.IF_ICMPNE, "Replaced if value is null with if ints are not equal"));
        MUTATIONS.put(Opcodes.IFNULL, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if value is null with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IFNULL, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if value is null with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IFNULL, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if value is null with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IFNULL, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if value is null with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IFNULL,
                new InsnSubstitution(Opcodes.IF_ACMPEQ, "Replaced if value is null with if references are equal"));
        MUTATIONS.put(Opcodes.IFNULL,
                new InsnSubstitution(Opcodes.IF_ACMPNE, "Replaced if value is null with if references are not equal"));
        MUTATIONS.put(Opcodes.IFNULL,
                new InsnSubstitution(Opcodes.IFEQ, "Replaced if value is null with if value is 0"));
        MUTATIONS.put(Opcodes.IFNULL,
                new InsnSubstitution(Opcodes.IFNE, "Replaced if value is null with if value is not 0"));
        MUTATIONS.put(Opcodes.IFNULL,
                new InsnSubstitution(Opcodes.IFLT, "Replaced if value is null with if value is less than 0"));
        MUTATIONS.put(Opcodes.IFNULL, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if value is null with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IFNULL, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if value is null with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IFNULL,
                new InsnSubstitution(Opcodes.IFGT, "Replaced if value is null with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IFNULL,
                new InsnSubstitution(Opcodes.IF_ICMPEQ, "Replaced if value is null with if ints are equal"));
        MUTATIONS.put(Opcodes.IFNULL,
                new InsnSubstitution(Opcodes.IFNONNULL, "Replaced if if value is null with if value is not null"));

        MUTATIONS.put(Opcodes.IFNONNULL,
                new InsnSubstitution(Opcodes.IF_ICMPNE, "Replaced if value is not null with if ints are not equal"));
        MUTATIONS.put(Opcodes.IFNONNULL, new InsnSubstitution(Opcodes.IF_ICMPLT,
                "Replaced if value is not null with if value1 is less than value2"));
        MUTATIONS.put(Opcodes.IFNONNULL, new InsnSubstitution(Opcodes.IF_ICMPGE,
                "Replaced if value is not null with if value1 is greater than or equal to value2"));
        MUTATIONS.put(Opcodes.IFNONNULL, new InsnSubstitution(Opcodes.IF_ICMPGT,
                "Replaced if value is not null with if value1 is greater than value2"));
        MUTATIONS.put(Opcodes.IFNONNULL, new InsnSubstitution(Opcodes.IF_ICMPLE,
                "Replaced if value is not null with if value1 is less than or equal to value2"));
        MUTATIONS.put(Opcodes.IFNONNULL,
                new InsnSubstitution(Opcodes.IF_ACMPEQ, "Replaced if value is not null with if references are equal"));
        MUTATIONS.put(Opcodes.IFNONNULL, new InsnSubstitution(Opcodes.IF_ACMPNE,
                "Replaced if value is not null with if references are not equal"));
        MUTATIONS.put(Opcodes.IFNONNULL,
                new InsnSubstitution(Opcodes.IFEQ, "Replaced if value is not null with if value is 0"));
        MUTATIONS.put(Opcodes.IFNONNULL,
                new InsnSubstitution(Opcodes.IFNE, "Replaced if value is not null with if value is not 0"));
        MUTATIONS.put(Opcodes.IFNONNULL,
                new InsnSubstitution(Opcodes.IFLT, "Replaced if value is not null with if value is less than 0"));
        MUTATIONS.put(Opcodes.IFNONNULL, new InsnSubstitution(Opcodes.IFGE,
                "Replaced if value is not null with if value is greater than or equal to 0"));
        MUTATIONS.put(Opcodes.IFNONNULL, new InsnSubstitution(Opcodes.IFLE,
                "Replaced if value is not null with if value is less than or equal to 0"));
        MUTATIONS.put(Opcodes.IFNONNULL,
                new InsnSubstitution(Opcodes.IFGT, "Replaced if value is not null with if value is greater than 0"));
        MUTATIONS.put(Opcodes.IFNONNULL,
                new InsnSubstitution(Opcodes.IFNULL, "Replaced if value is not null with if value is null"));
        MUTATIONS.put(Opcodes.IFNONNULL,
                new InsnSubstitution(Opcodes.IF_ICMPEQ, "Replaced if value is not null with if ints are equal"));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }

}
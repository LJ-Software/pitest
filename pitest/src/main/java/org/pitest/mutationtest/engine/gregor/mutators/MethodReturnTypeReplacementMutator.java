package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.pitest.classinfo.ClassName;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

/**
 * 
 *
 *
 *
 * 
 *
 */
public enum MethodReturnTypeReplacementMutator implements MethodMutatorFactory {

    RETURN_TYPE_REPLACEMENT;

    @Override
    public MethodVisitor create(MutationContext context, MethodInfo methodInfo, MethodVisitor methodVisitor) {
        if(!returnsAnObject(methodInfo)){
        return new ReturnTypeReplacementMethodVisitor(this, methodInfo, context, methodVisitor);
        } else {
            return methodVisitor;
        }
    }

    private boolean returnsAnObject(MethodInfo methodInfo) {
        final Type type = Type.getReturnType(methodInfo.getMethodDescriptor());
        return type.getClassName().equals("java.lang.Object");
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

class ReturnTypeReplacementMethodVisitor extends MethodCallMethodVisitor {

    static ZeroOperandMutation IntegerReturnMutation=new ZeroOperandMutation(){@Override public void apply(int opCode,MethodVisitor mv){
    // Pop the method call off of the stack
    mv.visitInsn(Opcodes.POP);
    // Push random integer onto stack
    mv.visitIntInsn(Opcodes.BIPUSH,getRandominteger());
    // Replace the method call with one that returns the correct type
    mv.visitMethodInsn(Opcodes.INVOKESTATIC,"java/lang/Integer","bitCount","(Ljava/lang/Integer;)Ljava/lang/Integer;", false);
    // Return an integer
    mv.visitInsn(Opcodes.IRETURN);}

    @Override public String decribe(int opCode,MethodInfo methodInfo){return"replaced the method call that returned an integer with another method for "+methodInfo.getDescription();}};

    static ZeroOperandMutation LongReturnMutation=new ZeroOperandMutation(){@Override public void apply(int opCode,MethodVisitor mv){
    // Pop the method call off of the stack
    mv.visitInsn(Opcodes.POP);
    // Push random integer onto stack
    mv.visitIntInsn(Opcodes.BIPUSH,getRandominteger());
    // Convert integer to long
    mv.visitInsn(Opcodes.I2L);
    // Replace the method call with one that returns the correct type
    mv.visitMethodInsn(Opcodes.INVOKESTATIC,"java/lang/Long","reverseBytes","(Ljava/lang/Long;)Ljava/lang/Long;", false);
    // Return an integer
    mv.visitInsn(Opcodes.LRETURN);}

    @Override public String decribe(int opCode,MethodInfo methodInfo){return"replaced the method call that returns a long with another method for "+methodInfo.getDescription();}};

    static ZeroOperandMutation StringReturnMutation=new ZeroOperandMutation(){@Override public void apply(int opCode,MethodVisitor mv){
    // Pop the method call off of the stack
    mv.visitInsn(Opcodes.POP);
    // Push random integer onto stack
    mv.visitIntInsn(Opcodes.BIPUSH,getRandominteger());
    // Replace the method call with one that returns the correct type
    mv.visitMethodInsn(Opcodes.INVOKESTATIC,"java/lang/Integer","toString","(Ljava/lang/Integer;)Ljava/lang/String;", false);
    // Return an integer
    mv.visitInsn(Opcodes.ARETURN);}

    @Override public String decribe(int opCode,MethodInfo methodInfo){return"replaced the method call that returns a String with another method for "+methodInfo.getDescription();}};

    static ZeroOperandMutation VoidReturnMutation=new ZeroOperandMutation(){@Override public void apply(int opCode,MethodVisitor mv){
    // Pop the method call off of the stack
    mv.visitInsn(Opcodes.POP);
    // Replace the method call with one that returns the correct type
    mv.visitMethodInsn(Opcodes.INVOKESTATIC,"java/lang/System","runFinalization","()V", false);
    // Return an integer
    mv.visitInsn(Opcodes.RETURN);}

    @Override public String decribe(int opCode,MethodInfo methodInfo){return"replaced the void method call with another for "+methodInfo.getDescription();}};

    static ZeroOperandMutation CharReturnMutation=new ZeroOperandMutation(){@Override public void apply(int opCode,MethodVisitor mv){
    // Pop the method call off of the stack
    mv.visitInsn(Opcodes.POP);
    // Push random integer onto stack
    mv.visitIntInsn(Opcodes.BIPUSH,getRandominteger());
    // Replace the method call with one that returns the correct type
    mv.visitMethodInsn(Opcodes.INVOKESTATIC,"java/lang/Character","highSurrogate","(Ljava/lang/Integer;)Ljava/lang/Character;", false);
    // Return an integer
    mv.visitInsn(Opcodes.ARETURN);}

    @Override public String decribe(final int opCode,final MethodInfo methodInfo){return"replaced the method call that returns a Character with another for "+methodInfo.getDescription();}};

    static ZeroOperandMutation ShortReturnMutation = new ZeroOperandMutation(){
        @Override
        public void apply(int opCode, MethodVisitor mv){
            // Pop the method call off of the stack
            mv.visitInsn(Opcodes.POP);
            // Push random integer onto stack
            mv.visitIntInsn(Opcodes.BIPUSH, getRandominteger());
            // convert integer to short
            mv.visitInsn(Opcodes.I2S);
            // Replace the method call with one that returns the correct type
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Short", "reverseBytes", "(Ljava/lang/Short;)Ljava/lang/Short;", false);
            // Return an integer
            mv.visitInsn(Opcodes.ARETURN);
        }

        @Override
        public String decribe(final int opCode, final MethodInfo methodInfo){
            return "replaced the method call that returns a Short with another for " + methodInfo.getDescription();
        }
    };

    static ZeroOperandMutation DoubleReturnMutation=new ZeroOperandMutation(){@Override public void apply(int opCode,MethodVisitor mv){
    // Pop the method call off of the stack
    mv.visitInsn(Opcodes.POP);
    // Push random integer onto stack
    mv.visitIntInsn(Opcodes.BIPUSH, getRandominteger());
    // Convert integer to long
    mv.visitInsn(Opcodes.I2L);
    // Replace the method call with one that returns the correct type
    mv.visitMethodInsn(Opcodes.INVOKESTATIC,"java/lang/Double","longBitsToDouble","(Ljava/lang/Long;)Ljava/lang/Double;", false);
    // Return an integer
    mv.visitInsn(Opcodes.DRETURN);}

    @Override public String decribe(int opCode,MethodInfo methodInfo){return"replaced the method call that returns a Double with another for "+methodInfo.getDescription();}};

    static ZeroOperandMutation FloatReturnMutation=new ZeroOperandMutation(){@Override public void apply(int opCode,MethodVisitor mv){
    // Pop the method call off of the stack
    mv.visitInsn(Opcodes.POP);
    // Push random integer onto stack
    mv.visitIntInsn(Opcodes.BIPUSH,getRandominteger());
    // Replace the method call with one that returns the correct type
    mv.visitMethodInsn(Opcodes.INVOKESTATIC,"java/lang/Float","intBitsToFloat","(Ljava/lang/Integer;)Ljava/lang/Float;", false);
    // Return an integer
    mv.visitInsn(Opcodes.FRETURN);}

    @Override public String decribe(int opCode,MethodInfo methodInfo){return"replaced the method call that returns a Float with another for "+methodInfo.getDescription();}};

    static final Map<ZeroOperandMutation, String> MUTATOR_POOL = new HashMap<>();
    static {
        MUTATOR_POOL.put(IntegerReturnMutation, "Int");
        MUTATOR_POOL.put(LongReturnMutation, "Long");
        MUTATOR_POOL.put(StringReturnMutation, "java.lang.String");
        MUTATOR_POOL.put(VoidReturnMutation, "Void");
        MUTATOR_POOL.put(CharReturnMutation, "Char");
        MUTATOR_POOL.put(ShortReturnMutation, "Short");
        MUTATOR_POOL.put(DoubleReturnMutation, "Double");
        MUTATOR_POOL.put(FloatReturnMutation, "Float");
    }

    static final Map<Integer, ZeroOperandMutation> FINAL_MUTATIONS = new HashMap<>();

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return FINAL_MUTATIONS;
    }

    @Override
        protected boolean canMutate(final int opcode) {
        return super.canMutate(opcode) && isAnAcceptableType();
  }

    private boolean isAnAcceptableType() {
        ZeroOperandMutation t = getCorrectMutation();
        if(t.decribe(1, methodInfo()) == null){
            return false;
        } else {
            return true;
        }   
    }

    private ZeroOperandMutation getCorrectMutation() {
        final Type t = Type.getReturnType(this.methodInfo().getMethodDescriptor());
        String objectClass = t.getClassName();
        System.out.println("RETURN TYPE FOR THIS METHOD IS: " + objectClass);
        Map<ZeroOperandMutation, String> matchingMethods = new HashMap<>();
        for (Map.Entry<ZeroOperandMutation, String> entry : MUTATOR_POOL.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(objectClass)) {
                matchingMethods.put(entry.getKey(), entry.getValue());
            }
        }
        Random rand = new Random();
        Map.Entry<ZeroOperandMutation, String> mutationEntry;
        ZeroOperandMutation mutation = new ZeroOperandMutation(){
        
            @Override
            public String decribe(int opCode, MethodInfo methodInfo) {
                return null;
            }
        
            @Override
            public void apply(int opCode, MethodVisitor mv) {
                
            }
        };
        int selectedMethodIndex = -1;
        if(matchingMethods.size() <= 0){
            return mutation;
        } else {
            selectedMethodIndex = rand.nextInt(matchingMethods.size());
        }
        int i = 0;
        for(Map.Entry<ZeroOperandMutation, String> entry : matchingMethods.entrySet()){
            if(i == selectedMethodIndex){
                mutation = entry.getKey();
                System.out.println("MUTATION FOUND FOR " + entry.getValue() + " :: " + methodInfo().getDescription());
                break;
            }
            else {
                i++;
                continue;
            }
        }
        return mutation;
    }

    ReturnTypeReplacementMethodVisitor(final MethodMutatorFactory factory, final MethodInfo methodInfo,
            final MutationContext context, final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
        FINAL_MUTATIONS.put(Opcodes.ARETURN, getCorrectMutation());
    }

    public static int getRandominteger() {
        Random rand = new Random();
        return rand.nextInt();
    }

   
}
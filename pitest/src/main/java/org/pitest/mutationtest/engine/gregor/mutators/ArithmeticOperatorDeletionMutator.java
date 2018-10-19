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
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

public enum ArithmeticOperatorDeletionMutator implements MethodMutatorFactory {

  ARITHMETIC_DELETION_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new ArithmeticDeleteMethodVisitor(this, methodInfo, context, methodVisitor);
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

class ArithmeticDeleteMethodVisitor extends AbstractInsnMutator {

  private static final String                     DESCRIPTION = "removed an operand";
  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<>();
  
  static {
     // All of the targeted statements
     MUTATIONS.put(Opcodes.DADD, returnFirstOperand(Opcodes.DADD));
     MUTATIONS.put(Opcodes.DDIV, returnFirstOperand(Opcodes.DDIV));
     MUTATIONS.put(Opcodes.DMUL, returnFirstOperand(Opcodes.DMUL));
     MUTATIONS.put(Opcodes.DREM, returnFirstOperand(Opcodes.DREM));
     MUTATIONS.put(Opcodes.DSUB, returnFirstOperand(Opcodes.DSUB));
 
     MUTATIONS.put(Opcodes.FADD, returnFirstOperand(Opcodes.FADD));
     MUTATIONS.put(Opcodes.FDIV, returnFirstOperand(Opcodes.FDIV));
     MUTATIONS.put(Opcodes.FMUL, returnFirstOperand(Opcodes.FMUL));
     MUTATIONS.put(Opcodes.FREM, returnFirstOperand(Opcodes.FREM));
     MUTATIONS.put(Opcodes.FSUB, returnFirstOperand(Opcodes.FSUB));
 
     MUTATIONS.put(Opcodes.IADD, returnFirstOperand(Opcodes.IADD));
     MUTATIONS.put(Opcodes.IDIV, returnFirstOperand(Opcodes.IDIV));
     MUTATIONS.put(Opcodes.IMUL, returnFirstOperand(Opcodes.IMUL));
     MUTATIONS.put(Opcodes.IREM, returnFirstOperand(Opcodes.IREM));
     MUTATIONS.put(Opcodes.ISUB, returnFirstOperand(Opcodes.ISUB));
 
     MUTATIONS.put(Opcodes.LADD, returnFirstOperand(Opcodes.LADD));
     MUTATIONS.put(Opcodes.LDIV, returnFirstOperand(Opcodes.LDIV));
     MUTATIONS.put(Opcodes.LMUL, returnFirstOperand(Opcodes.LMUL));
     MUTATIONS.put(Opcodes.LREM, returnFirstOperand(Opcodes.LREM));
     MUTATIONS.put(Opcodes.LSUB, returnFirstOperand(Opcodes.LSUB));

     MUTATIONS.put(Opcodes.DADD, returnSecondOperand(Opcodes.DADD));
     MUTATIONS.put(Opcodes.DDIV, returnSecondOperand(Opcodes.DDIV));
     MUTATIONS.put(Opcodes.DMUL, returnSecondOperand(Opcodes.DMUL));
     MUTATIONS.put(Opcodes.DREM, returnSecondOperand(Opcodes.DREM));
     MUTATIONS.put(Opcodes.DSUB, returnSecondOperand(Opcodes.DSUB));
 
     MUTATIONS.put(Opcodes.FADD, returnSecondOperand(Opcodes.FADD));
     MUTATIONS.put(Opcodes.FDIV, returnSecondOperand(Opcodes.FDIV));
     MUTATIONS.put(Opcodes.FMUL, returnSecondOperand(Opcodes.FMUL));
     MUTATIONS.put(Opcodes.FREM, returnSecondOperand(Opcodes.FREM));
     MUTATIONS.put(Opcodes.FSUB, returnSecondOperand(Opcodes.FSUB));
 
     MUTATIONS.put(Opcodes.IADD, returnSecondOperand(Opcodes.IADD));
     MUTATIONS.put(Opcodes.IDIV, returnSecondOperand(Opcodes.IDIV));
     MUTATIONS.put(Opcodes.IMUL, returnSecondOperand(Opcodes.IMUL));
     MUTATIONS.put(Opcodes.IREM, returnSecondOperand(Opcodes.IREM));
     MUTATIONS.put(Opcodes.ISUB, returnSecondOperand(Opcodes.ISUB));
 
     MUTATIONS.put(Opcodes.LADD, returnSecondOperand(Opcodes.LADD));
     MUTATIONS.put(Opcodes.LDIV, returnSecondOperand(Opcodes.LDIV));
     MUTATIONS.put(Opcodes.LMUL, returnSecondOperand(Opcodes.LMUL));
     MUTATIONS.put(Opcodes.LREM, returnSecondOperand(Opcodes.LREM));
     MUTATIONS.put(Opcodes.LSUB, returnSecondOperand(Opcodes.LSUB));
  }
  

  ArithmeticDeleteMethodVisitor(final MethodMutatorFactory factory,
      final MethodInfo methodInfo, final MutationContext context,
      final MethodVisitor writer) {
      super(factory, methodInfo, context, writer);
  }



  private static ZeroOperandMutation returnSecondOperand(int op) {
    return new ZeroOperandMutation() {
      @Override
      public void apply(final int opCode, final MethodVisitor mv) {
        switch (op) {
          // 32 bit values
          case Opcodes.FADD:
          case Opcodes.FDIV:
          case Opcodes.FMUL:
          case Opcodes.FREM:
          case Opcodes.FSUB:
          case Opcodes.IADD:
          case Opcodes.IDIV:
          case Opcodes.IMUL:
          case Opcodes.IREM:
          case Opcodes.ISUB:
          mv.visitVarInsn(op, 1);
 
          break;

          // 64 bit values
          case Opcodes.DADD:
          case Opcodes.DDIV:
          case Opcodes.DMUL:
          case Opcodes.DREM:
          case Opcodes.DSUB:
          case Opcodes.LADD:
          case Opcodes.LDIV:
          case Opcodes.LMUL:
          case Opcodes.LREM:
          case Opcodes.LSUB:
          mv.visitVarInsn(op, 2);
          break;
        }
      }

      @Override
      public String decribe(final int opCode, final MethodInfo methodInfo) {
        return "replaced arithmetic return with operand 2 for " + methodInfo.getDescription();
      }
    };
  }

  private static ZeroOperandMutation returnFirstOperand(int op) {
    return new ZeroOperandMutation() {
      @Override
      public void apply(final int opCode, final MethodVisitor mv) {  
          mv.visitVarInsn(op, 2);
      }

      @Override
      public String decribe(final int opCode, final MethodInfo methodInfo) {
        return "replaced arithmetic return with operand 1 for " + methodInfo.getDescription();
      }
    };
  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS; 
  }
}
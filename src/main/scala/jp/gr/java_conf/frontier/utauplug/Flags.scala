package jp.gr.java_conf.frontier.utauplug
import scala.util.control.Exception._
import scala.collection.mutable.HashMap

/**
 * Flags�������N���X
 */
class Flags extends HashMap[String, Int] {
  def calclate(c: Flags.Calculator): Flags = {
    val f = new Flags
    for ((k, v) <- this) f += (k -> v)
    for ((k, (op, v)) <- c) {
      f += (k -> Calc.run(getOrElse(k, 0), op, v))
    }
    f
  }

  /**
   * Flags������ɕϊ��B���̕���������̂܂�UTAU��Flags�w��Ɏg����
   */
  override def toString: String = {
    val buf = new StringBuilder
    for ((k, v) <- this) buf ++= k + v.toString()
    buf.toString()
  }
}

/**
 * Flags����ьv�Z���[���̍쐬�p
 */
object Flags {
  type Calculator = HashMap[String, (Calc.Value, Int)]

  private def isVal(c: Char): Boolean = (c.isDigit || c == '-')
  private def to(v: String): Int = allCatch opt v.toInt getOrElse 0

  /**
   * �����񂩂�Flags�N���X�𐶐�
   */
  def parse(str: String): Flags = {
    def getKV(s: String, f: Flags): Flags = {
      if (s.nonEmpty) {
        val (k, ss) = s.span(!isVal(_))
        val (v, sss) = ss.span(isVal(_))
        f += (k -> to(v))
        getKV(sss, f)
      }
      f
    }
    getKV(str, new Flags())
  }

  /**
   * �����񂩂�Flags�v�Zmap���쐬�B
   * ������(key + ���Z�q(Calc,isOp�ŗL���Ȃ���) + �l)�̗���
   * ���Z�q�ȗ�����Calc.Set�ƂȂ�B
   * �܂����Z�q����ȏ�ݒ肵���ۂ̓���͕ۏ؂��Ă��Ȃ��B
   */
  def calculator(str: String): Calculator = {
    def getKV(s: String, f: Calculator): Calculator = {
      if (s.nonEmpty) {
        val (k, ss) = s.span(a => !isVal(a) && !Calc.isOp(a))
        val (opc, sss) = ss.span(Calc.isOp(_))
        val op = if (0 < opc.size) Calc(opc.toString()) else Calc.Set
        val (v, ssss) = sss.span(isVal(_))
        f += (k -> (op, to(v)))
        getKV(ssss, f)
      }
      f
    }
    getKV(str, new Calculator())
  }
}

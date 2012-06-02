package jp.gr.java_conf.frontier.utauplug
import scala.util.control.Exception._

/**
 * ust�̉����v�f�����\���B[#SETTING]�ɂ��Ă͕ʈ���
 */
class UtauElement(
  val blockName: String = "#INSERT",
  val attrMap: Map[String, String] = Map.empty[String, String]) {

  def this(attr: Map[String, String]) = this("#INSERT", attr)
  /**
   * �����̒l��Ԃ��B���݂��Ȃ��ꍇ��""��Ԃ��B
   * �A�N�Z�b�T���p�ӂ���Ă���ꍇ�͂�������g���������ǂ�
   */
  def attr(name: String): String = {
    if (attrMap.keySet.contains(name)) attrMap(name) else ""
  }
  //
  //  /**
  //   * �����̒l��ݒ肷��B�ݒ��͎��g��Ԃ��B
  //   * �A�N�Z�b�T���p�ӂ���Ă���ꍇ�͂�������g���������ǂ�
  //   * @return this
  //   */
  //  def attr(name: String, value: String) {
  //    attrMap += name -> value
  //  }
  //
  //  /** �S�v�f���폜 */
  //  def clear() { attrMap.keySet.foreach(clear(_)) }
  //
  //  /**
  //   * ����̗v�f�̂ݍ폜
  //   * �폜���ʂ𖾎����Ȃ���UTAU���ŏ����Ȃ����߁A""���Z�b�g���Ă���
  //   */
  //  def clear(name: String) { attr(name, "") }

  /** @return �x�������Ȃ�true */
  def isRest: Boolean = lyric == "R"

  /** @return ���ݑI�𒆈����̗v�f�Ȃ�true */
  def isSelected: Boolean = blockName match {
    case "#PREV" | "#NEXT" => false
    case _ => true
  }

  def nl: String = "\n"

  //output
  def output(sb: StringBuilder) {
    sb.append("[" + blockName + "]" + nl)
    attrMap.foreach { case (k, v) => sb.append(k + "=" + v + nl) }
  }

  //attr accessor

  def lyric: String = attr("Lyric")
  //  def lyric_=(value: String) { attr("Lyric", value) }

  def noteNum: Int = allCatch opt attr("NoteNum").toInt getOrElse 0
  //  def noteNum_=(value: Int) { attr("NoteNum", value.toString) }

  /** NoteNum����쐬����Note�N���X��Ԃ� */
  def note: Note = Note(noteNum)
  /** Note��num����NoteNum��ݒ� */
  //  def note_=(value: Note) { noteNum = value.num }

  def length: Int = allCatch opt attr("Length").toInt getOrElse 0
  //  def length_=(value: Int) { attr("Length", value.toString()) }

  /** @return Intensity�̒l�A���ݒ�̏ꍇ��100��Ԃ�*/
  def intensity: Int = allCatch opt attr("Intensity").toInt getOrElse 100
  /** Intensity�̒l���Z�b�g�A0�`200�͈̔͂Ɋۂ߂���*/
  //  def intensity_=(value: Int) { attr("Intensity", value.max(0).min(200).toString()) }

  /** @return moduration�̒l�A���ݒ�̏ꍇ��0��Ԃ�*/
  def moduration: Int = allCatch opt attr("Moduration").toInt getOrElse 0
  /** moduration�̒l���Z�b�g�A-200�`200�͈̔͂Ɋۂ߂���*/
  //  def moduration_=(value: Int) { attr("Moduration", value.max(-200).min(200).toString()) }

  def builder: UtauElementBuilder = new UtauElementBuilder(this)
}
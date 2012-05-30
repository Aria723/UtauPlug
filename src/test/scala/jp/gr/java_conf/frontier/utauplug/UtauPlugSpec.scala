package jp.gr.java_conf.frontier.utauplug

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class UtauPlugSpec extends Specification {

  "fromFile" should {
    val a = "src/test/scala/jp/gr/java_conf/frontier/utauplug/UtauPlug_test.ust"
    val plug = UtauPlug.fromFile(a)

    "��ڂ̗v�f���擾�ł��Ă��邱��" in {
      plug.list.size must_== 22
      plug.list(0).lyric must_== "��"
      plug.list(0).blockName must_== "#0004"
    }
    "�v�f�����ԂɎ擾�ł��Ă��邱��" in {
      plug.list(0).lyric must_== "��"
      plug.list(1).lyric must_== "��"
      plug.list(2).lyric must_== "��"
      plug.list(3).lyric must_== "R"

    }

  }

  "node" should {
    val a = "src/test/scala/jp/gr/java_conf/frontier/utauplug/UtauPlug_test.ust"
    val plug = UtauPlug.fromFile(a)
    "prev�̈�O���擾���悤�Ƃ����Ƃ�" in {
      plug.node(0).prev.prev.isEmpty must_== true
    }
    "��ڂ̃m�[�h����PREV�擾" in {
      plug.node(0).prev.get.lyric must_== "��"
    }
    "��ڂ̃m�[�h�擾" in {
      plug.node(0).get.lyric must_== "��"
    }
    "��ڂ̃m�[�h����O�̃m�[�h�擾" in {
      plug.node(1).prev.get.lyric must_== "��"
    }
    "��ڂ̃m�[�h���玟�̃m�[�h�擾" in {
      plug.node(0).next.get.lyric must_== "��"
    }
    "�Ō�̃m�[�h���玟�̃m�[�h�iNEXT�j�擾" in {
      plug.node(plug.list.size-1).next.get.lyric must_== "��"
    }

  }

  //output
}
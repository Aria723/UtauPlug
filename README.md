UtauPlug
======================
Utau�v���O�C�����쐬����ۂɖʓ|��ust�t�@�C���̓ǂݍ��݂�A�ꊇ�������ȒP�ɍs�����߂̃v���O�C���ł��B
�ȑO��.Net�ɂĊJ�������Ă����v���W�F�N�g�̈ڐA�`���P�łƂȂ�܂��B

### �Ȃ�Scala�H ###

1. Mac�łւ̓W�J�����܂��Ajar�ɂ��v���O�C���񋟂����
2. �������낻������������

�g����
----

	//�t�@�C���p�X���w�肵�ēǂݍ��݁B�N������args���p��z��
	val plug = UtauPlug.fromFile(filePath)
	//exec���s�Ŋe�v�f�����[�v�ł���
	val plug2 = plug.exec { e =>
	  //���ɏC�������Ȃ��ꍇ��add���s�ł��̂܂܂̒l������
	  e.add()
	  //e.node��node�擾�Bnode.get�őΏۂ̗v�f�Anode.prev,node.next�őO��̗v�f������
	  val elm = e.node.get
	  //builder���ĂԂ��Ƃŗv�f�𑀍�ł���
	  val b = elm.bulder
	  b.intensity += 10
	  //�ύX��̗v�f��add
	  e.add(b.build)
	  //�V�K�̗v�f��ǉ����邱�Ƃ��ł���
	  e.add(new UtauElement(Map("Intensity" -> "10", "Lyric" -> "�Ă�")))
	}

���̂��������܂��B

import dis, marshal
f = open("netlab.pyc", "rb").read()
print(f)
code = marshal.loads(f[16:])

dis.dis(code)
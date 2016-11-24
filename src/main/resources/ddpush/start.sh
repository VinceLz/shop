#!/bin/bash
JAVA_OPTS=" -Xmx1g "
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  TARGET="$(readlink "$SOURCE")"
  if [[ $SOURCE == /* ]]; then
    echo "SOURCE '$SOURCE' is an absolute symlink to '$TARGET'"
    SOURCE="$TARGET"
  else
    DIR="$( dirname "$SOURCE" )"
    echo "SOURCE '$SOURCE' is a relative symlink to '$TARGET' (relative to '$DIR')"
    SOURCE="$DIR/$TARGET" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
  fi
done
#echo "SOURCE is '$SOURCE'"
RDIR="$( dirname "$SOURCE" )"
DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
#if [ "$DIR" != "$RDIR" ]; then
#  echo "DIR '$RDIR' resolves to '$DIR'"
#fi
echo "Using Duser.dir: '$DIR'"
echo "Using CLASSPATH: '$DIR/lib'"
echo "Using JAVA_OPTS: '$JAVA_OPTS'"
echo "Using LOG  PATH: '$DIR/logs'"
echo ""

java -Duser.dir=$DIR $JAVA_OPTS -classpath $DIR/lib/*.jar org.ddpush.im.v1.node.IMServer 2>&1 >> $DIR/logs/ddpush.out &

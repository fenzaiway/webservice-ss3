/**
 * 首页
 * @param startIndex
 * @return
 */
function indexPage(startIndex)
{
	submit(startIndex);
}

/**
 * 尾页
 * @param startIndex
 * @return
 */
function lastIndex(startIndex)
{
	submit(startIndex);
}

/**
 * 下一页
 * @param startIndex
 * @return
 */
function nextIndex(startIndex)
{
	submit(startIndex);
}


/**
 * 上一页
 * @param startIndex
 * @return
 */
function preIndex(startIndex)
{
	submit(startIndex);
}

function gotoPage(startIndex)
{
	submit(startIndex);
}

function submit(startIndex)
{
	$("#startIndex").val(startIndex);
	$("#form").submit();
}

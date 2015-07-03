	.file	"openshell_demo.c"
	.section	.rodata
.LC0:
	.string	"-l"
.LC1:
	.string	"/bin/sh"
.LC2:
	.string	"-e"
.LC3:
	.string	"/usr/bin/xterm"
.LC4:
	.string	"Fork faild"
	.text
	.globl	main
	.type	main, @function
main:
.LFB2:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$16, %rsp
	call	fork
	movl	%eax, -4(%rbp)
	cmpl	$0, -4(%rbp)
	jne	.L2
	movl	$0, %r9d
	movl	$.LC0, %r8d
	movl	$.LC1, %ecx
	movl	$.LC2, %edx
	movl	$.LC3, %esi
	movl	$.LC3, %edi
	movl	$0, %eax
	call	execl
	movl	$-1, %edi
	call	_exit
.L2:
	cmpl	$0, -4(%rbp)
	jns	.L3
	movl	$.LC4, %edi
	call	puts
	movl	$-1, -8(%rbp)
	jmp	.L4
.L3:
	leaq	-8(%rbp), %rcx
	movl	-4(%rbp), %eax
	movl	$0, %edx
	movq	%rcx, %rsi
	movl	%eax, %edi
	movl	$0, %eax
	call	waitpid
	cmpl	-4(%rbp), %eax
	je	.L4
	movl	$-1, -8(%rbp)
.L4:
	movl	-8(%rbp), %eax
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE2:
	.size	main, .-main
	.ident	"GCC: (Ubuntu 4.8.2-19ubuntu1) 4.8.2"
	.section	.note.GNU-stack,"",@progbits

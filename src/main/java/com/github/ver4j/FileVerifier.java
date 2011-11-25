package com.github.ver4j;

import javax.annotation.Nonnull;

import com.github.ver4j.FileVerifier.FileInternal;

public class FileVerifier extends AObjectVerifierAwareVerifier<FileInternal> {

	public interface FileInternal {
	}

	private class NotVerifyingInternal implements FileInternal {
	}

	private class VerifyingInternal implements FileInternal {
	}

	public FileVerifier(@Nonnull ObjectVerifier objectVerifier) {
		super(objectVerifier);
	}

	@Override
	protected FileInternal newNotVerifyingInternal() {
		return new NotVerifyingInternal();
	}

	@Override
	protected FileInternal newVerifyingInternal() {
		return new VerifyingInternal();
	}
}
